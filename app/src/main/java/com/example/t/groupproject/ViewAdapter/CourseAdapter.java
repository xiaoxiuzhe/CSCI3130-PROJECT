package com.example.t.groupproject.ViewAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.t.groupproject.Model.Course;
import com.example.t.groupproject.R;
import com.example.t.groupproject.View.SectionLists;

import java.util.List;

/**
 * RecyclerView Adapter that binds data and interations with a list of course
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{

    private Context mCtx;
    private List<Course> courseList;

    public CourseAdapter(Context mCtx, List<Course> courseList) {
        this.mCtx = mCtx;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseViewHolder holder, int position) {
        final Course course = courseList.get(position);

        String courseName = course.getCourseName().replace("_", " ");
        holder.textViewTitle.setText(courseName);

        holder.textViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mCtx, "You clicked "+holder.textViewTitle.getText().toString(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mCtx, SectionLists.class);
                intent.putExtra("COURSENAME", course.getCourseName());
                Activity activity = (Activity) mCtx;
                intent.putExtra("FACULTY", activity.getTitle().toString().replace(" ","_"));
                mCtx.startActivity(intent);
            }
        });;
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;

        public CourseViewHolder(final View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);

        }
    }
}
