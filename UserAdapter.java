package com.example.betti.roompersistencelibrary;

/**
 * Created by Betti on 19.12.17.
 */

//gg

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViwHolder> {

    private List<UserBooks> users;

    UserAdapter(List<UserBooks> users) {
        this.users = users;
    }

    @Override
    public UserViwHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, viewGroup, false);
        return new UserViwHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(UserViwHolder userViwHolder, int position) {
        UserBooks userBooks = users.get(position);
        userViwHolder.address.setText(userBooks.address.toString());
        userViwHolder.borroweBook.setText(userBooks.borrowe + " book");
        userViwHolder.userId.setText("" + userBooks.userId);
        userViwHolder.userName.setText(userBooks.firstName);
    }

    void setUsers(List<UserBooks> newUser) {
        UserDiffUtil userDiffUtil =new UserDiffUtil(users,newUser);
        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(userDiffUtil);
        users.clear();
        users.addAll(newUser);
        diffResult.dispatchUpdatesTo(this);
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViwHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_id)
        TextView userId;
        @BindView(R.id.user_name)
        TextView userName;

        @BindView(R.id.borrowe_book)
        TextView borroweBook;

        @BindView(R.id.user_address)
        TextView address;

        UserViwHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class UserDiffUtil extends DiffUtil.Callback {

        private List<UserBooks> oldList, newList;

        UserDiffUtil(List<UserBooks> oldList, List<UserBooks> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int i, int i1) {
            if (oldList.get(i).userId == newList.get(i1).userId)
                return true;
            else
                return false;
        }

        @Override
        public boolean areContentsTheSame(int i, int i1) {
            if (oldList.get(i).equals(newList.get(i1))) {
                return true;
            } else {
                return false;
            }
        }
    }
}
