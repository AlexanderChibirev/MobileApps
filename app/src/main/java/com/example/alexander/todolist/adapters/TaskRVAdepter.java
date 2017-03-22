package com.example.alexander.todolist.adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.alexander.todolist.R;
import com.example.alexander.todolist.mvp.models.Task;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.example.alexander.todolist.R.id.checkBox;

public class TaskRVAdepter extends RecyclerView.Adapter<TaskRVAdepter.TaskViewHolder> implements RealmChangeListener {

    private RealmResults<Task> mTasks;
    private OnItemClickListener mOnItemClickListener;
    private OnCheckBoxClickListener mOnCheckBoxClickListener;

    public TaskRVAdepter(RealmResults<Task> tasks) {
        mTasks = tasks;
       // sortingDB();
        mTasks.addChangeListener(this);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_model, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = mTasks.get(position);
        holder.itemTitle.setText(task.getTitle());
        holder.itemDescription.setText(task.getDescription());
        holder.itemDate.setText(task.getTaskCompletionDate());
        holder.itemCheckBox.setChecked(task.getIsCompleted());
        holder.itemPriority.setText(getPriorityString(holder.view, task));
        initListenerForView(holder, position);
        initListenerForCheckBox(holder, position);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    @Override
    public void onChange(Object element) {        //добавить сортировку
        //sortingDB();
        notifyDataSetChanged();
    }

    private void sortingDB() {
        mTasks = mTasks.sort(
                "mIsCompleted", Sort.ASCENDING,
                "mPriority", Sort.ASCENDING);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnCheckBoxClickListener(OnCheckBoxClickListener listener) {
        mOnCheckBoxClickListener = listener;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemDescription;
        TextView itemDate;
        TextView itemPriority;

        View view;
        CheckBox itemCheckBox;

        TaskViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            itemDescription = (TextView) itemView.findViewById(R.id.itemDescription);
            itemDate = (TextView) itemView.findViewById(R.id.itemDate);
            itemCheckBox = (CheckBox) itemView.findViewById(checkBox);
            itemPriority = (TextView) itemView.findViewById(R.id.itemPriority);
        }
    }

    private String getPriorityString(View view, Task task) {
        Resources res = view.getResources();
        String[] priorities = res.getStringArray(R.array.priority_array);
        return res.getString(R.string.priority) + priorities[task.getPriority()];
    }


    private void initListenerForView(TaskViewHolder holder, int position) {
        if (mOnItemClickListener != null) {
            holder.view.setOnClickListener(view ->
                    mOnItemClickListener.onItemClick(position));
            holder.view.setOnLongClickListener(view ->
                    mOnItemClickListener.onLongItemClick(position));
        }
    }

    private void initListenerForCheckBox(TaskViewHolder holder, int position) {
        if (mOnCheckBoxClickListener != null) {
            holder.itemCheckBox.setOnClickListener(checkBox ->
                    mOnCheckBoxClickListener.onClick(position));
        }
    }
}
