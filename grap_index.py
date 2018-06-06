import codecs, re, os
import urllib.request
import pandas as pd


def get_content_block(input, start_word, end_word):
    before = ""
    content = ""
    after = ""
    start = False
    end = False
    for line in input.splitlines():
        if start and (end_word in line):
            end = True
        if start_word in line:
            start = True
        if start and not end:
            content = content + "\n" + line
        elif end:
            after = after + "\n" + line
        else:
            before = before + "\n" + line

    return before, content, after


def get_url(raw_table):
    pattern = r'<a href=\"(.+?)\">(.*?)</a>'
    matchObj = re.findall(pattern, raw_table)
    newobj = []
    for x in matchObj:
        newobj.append((x[0].replace("amp;", ''), x[1]))
    return newobj


def get_local_content(path):
    with codecs.open(path, 'r', errors='ignore') as f:
        content = f.read()

    return content


def create_dir(base, name):
    if name:
        path = os.path.join(base, name)
    else:
        path = base
    if not os.path.exists(path):
        os.makedirs(path)
    return path


class Course(object):
    def __init__(self, name, raw_content, path):
        self.name = name
        self.content = raw_content
        self.path = path
        self.row_contents = []
        self.__get_rows()
        self.parse_rows()

    def __get_rows(self):
        content = self.content
        while content:
            _, row_raw_content, content = self.__get_row(content)
            if row_raw_content:
                self.row_contents.append(row_raw_content)

    def parse_rows(self):
        rows = []
        link = ""
        l = len(self.row_contents)
        for index in range(0, l):
            content = self.row_contents[index]
            if index == 0:
                flag = 1
                link = self.__get_link_from_block(content)
            else:
                if "NOTE" in content:
                    flag = 3
                else:
                    flag = 2
                    row = self.parse_row(content, flag)
                    rows.append(row)

        df = pd.DataFrame(rows)

        path = self.path + ".csv"
        path_meta = self.path + "_meta" + ".txt"
        with open(path_meta, 'w') as f:
            f.write(link)
        df.to_csv(path, sep=",")

    def __get_link_from_block(self, content):
        content = content.splitlines()
        link = ""
        pattern = r'<a href=\"(.*?)\"    target="_blank" >'
        for line in content:
            link = self._get_content(line, pattern)
            if link:
                break
        return link

    def parse_row(self, content, flag):
        table_line = None
        if flag == 1:
            pass
        elif flag == 2:
            patterns = [None,
                        ("crn", r'<b>(\d+)</b>'),
                        ("section_code", r'>(.*?)<'),
                        ("section_type", r'>(.*?)<'),
                        ("crHrs", r'>(\d?)<'), None,
                        ("mo", r'<p class=\"centeraligntext\">(\w)</p>'),
                        ("tu", r'<p class=\"centeraligntext\">(\w)</p>'),
                        ("we", r'<p class=\"centeraligntext\">(\w)</p>'),
                        ("th", r'<p class=\"centeraligntext\">(\w)</p>'),
                        ("fr", r'<p class=\"centeraligntext\">(\w)</p>'),
                        ("times", r'>(.*?)<'),
                        ("location", r'>(.*?)<'),
                        ("max", r'>(\d+)</'),
                        ("cur", r'>(\d+)</'),
                        ("avail", r'>(\d+)</'),
                        ("wtlist", r'>(\d+)</'),
                        None,
                        None,
                        ("instructor", r'>(.*?)<'),
                        ("code", r'>(.*?)<'),
                        ("bhrs", r'>(\d+)</')]

            table_line = {"crn": "",
                          "section_code": "",
                          "section_type": "",
                          "crHrs": "",
                          "mo": "",
                          "tu": "",
                          "we": "",
                          "th": "",
                          "fr": "",
                          "times": "",
                          "location": "",
                          "max": "",
                          "cur": "",
                          "avail": "",
                          "wtlist": "",
                          "instructor": "",
                          "code": "",
                          "bhrs": ""}
            l = len(patterns)
            index = 0
            p = r'<td(.*?)/td>'
            instructor_name = False
            for line in content.splitlines():
                match = re.search(p, line)
                if match or instructor_name:
                    if patterns[index]:
                        name = patterns[index][0]
                        if not instructor_name:
                            table_line[name] = self._get_content(line, patterns[index][1])
                        else:
                            table_line[name] = line
                            instructor_name = False
                    index = index + 1
                elif str('<TD CLASS=\"dettl\">') == line:
                    instructor_name = True
                if index > l:
                    break
        else:
            table_line = None
        return table_line

    def _get_content(self, line, pattern):
        match = re.search(pattern, line)
        res = ""
        if match:
            res = match.group(1)
        return res

    def __get_row(self, content, start_word="<tr>", end_word="</tr>"):
        table_content_before, table_content, table_content_after = get_content_block(content, start_word, end_word)
        return table_content_before, table_content, table_content_after


class Subject(object):
    def __init__(self, name, link, path):
        self.name = name
        self.path = path
        self.base_link = "https://dalonline.dal.ca/PROD/"
        self.root_link = link
        self.children_links = []
        self.__create_dir()
        self.__create_children_links()
        self.course_raw_content = []
        self.__generate_all_course_raw_content()
        self.course_names = []
        self.create_courses()

    def __create_children_links(self):
        content = self.__get_url_page_content(self.root_link)
        table_content_before, table_content, table_content_after = self.__get_page_block(content)
        if table_content:
            links = get_url(table_content)
            for link, page in links:
                url = self.base_link + link
                self.children_links.append(url)

    def __get_page_block(self, raw_page, start_word="<center>Page", end_word="</center>"):
        table_content_before, table_content, table_content_after = get_content_block(raw_page, start_word, end_word)

        return table_content_before, table_content, table_content_after

    def __get_url_page_content(self, url):
        response = urllib.request.urlopen(url)
        content = response.read()
        content = content.decode('utf-8')
        return content

    def __create_dir(self):
        path = create_dir(self.path, "")
        return path

    def __get_course_blocks(self, content):
        _, _, after = self.__get_dataentrytable_table(content)
        _, course_content, _ = self.__get_dataentrytable_table(after)
        return course_content

    def __get_course_raw_content(self, content):
        while content:
            _, course_raw_content, content = self.__get_course_table(content)
            course_raw_content = "<tr>\n<TD>\n" + course_raw_content
            self.course_raw_content.append(course_raw_content)

    def __generate_all_course_raw_content(self):
        content = self.__get_url_page_content(self.root_link)
        table_content = self.__get_course_blocks(content)
        self.__get_course_raw_content(table_content)
        for link in self.children_links:
            content = self.__get_url_page_content(link)
            table_content = self.__get_course_blocks(content)
            self.__get_course_raw_content(table_content)
        return self.course_raw_content

    def create_courses(self):
        obj_list = []
        for x in self.course_raw_content:
            obj_list.append(self.create_course(x))
        return

    def create_course(self, content):
        content_list = content.splitlines()
        pattern = r'<b>(.*?)</b>'
        name = ""
        for line in content_list:
            match = re.search(pattern, line)
            if match:
                name = match.group(1)
                break
        suffix = 1
        obj = None
        if name:
            name = name.replace(" ", "_")
            name = name.replace("/", "_")
            while name in self.course_names:
                name = name + str(suffix)
                suffix = suffix + 1
            self.course_names.append(name)

            path = os.path.join(self.path, name)
            obj = Course(name, content, path)
        return obj

    def __get_course_table(self, raw_page, start_word="hyggfor2.gif", end_word="hyggfor2.gif"):
        table_content_before, table_content, table_content_after = get_content_block(raw_page, start_word, end_word)

        return table_content_before, table_content, table_content_after

    def __get_dataentrytable_table(self, raw_page, start_word="dataentrytable", end_word="/table"):
        table_content_before, table_content, table_content_after = get_content_block(raw_page, start_word, end_word)

        return table_content_before, table_content, table_content_after


class SubjectBuilder(object):
    def __init__(self, source_dir, output_dir):
        self.source_dir = source_dir
        self.paths = {}
        self.output_dir = output_dir
        self.subject_object_list = []

    def __get_local_page_content(self, path):
        with codecs.open(path, 'r', errors='ignore') as f:
            content = f.read()
        return content

    def create_object(self):
        if not self.paths:
            self.__get_page_paths()

        for session_name, path in self.paths.items():
            content = self.__get_local_page_content(path)
            _, table_content, _ = self.__get_dataentrytable_table(content)
            urls = get_url(table_content)
            seesion_path = self.__create_dir(session_name)
            for url, subject_name in urls:
                path_name = subject_name.replace(" ", "_")
                path_name = path_name.replace("/", "_")
                subject_path = os.path.join(seesion_path, path_name)
                subj = Subject(subject_name, url, subject_path)
                self.subject_object_list.append(subj)
        return self.subject_object_list

    def __get_dataentrytable_table(self, raw_page, start_word="dataentrytable", end_word="/table"):
        table_content_before, table_content, table_content_after = get_content_block(raw_page, start_word, end_word)

        return table_content_before, table_content, table_content_after

    def __create_dir(self, name):
        path = create_dir(self.output_dir, name)
        return path

    def __get_page_paths(self):
        names = os.listdir(self.source_dir)
        suffix = ".htm"
        suffix_len = len(suffix)
        for name in names:
            if name.endswith(suffix):
                path = os.path.join(self.source_dir, name)
                name = name[:-suffix_len]
                self.paths[name] = path


def main():
    dir_path = os.path.dirname(os.path.realpath(__file__))
    source = os.path.join(dir_path, 'resource')
    page_output = os.path.join((dir_path), 'page_output')
    builder = SubjectBuilder(source, page_output)

    subjects = builder.create_object()


main()
