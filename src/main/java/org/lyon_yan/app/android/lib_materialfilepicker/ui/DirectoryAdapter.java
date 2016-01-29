package org.lyon_yan.app.android.lib_materialfilepicker.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import org.lyon_yan.app.android.lib_materialfilepicker.utils.FileTypeUtils;
import org.lyon_yan.app.android.lib_materialfilepicker.R;

import java.io.File;
import java.util.List;

/**
 * Created by Dimorinny on 24.10.15.
 */

public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.DirectoryViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }

    public class DirectoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView mFileImage;
        private TextView mFileTite;
        private TextView mFileSubtitle;

        public DirectoryViewHolder(View itemView, final OnItemClickListener clickListener,final OnItemLongClickListener longClickListener) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v, getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return longClickListener.onItemLongClick(v,getAdapterPosition());
                }
            });
            mFileImage = (ImageView) itemView.findViewById(R.id.item_file_image);
            mFileTite = (TextView) itemView.findViewById(R.id.item_file_title);
            mFileSubtitle = (TextView) itemView.findViewById(R.id.item_file_subtitle);
        }
    }

    private List<File> mFiles;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public DirectoryAdapter(Context context, List<File> files) {
        mContext = context;
        mFiles = files;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }
    @Override
    public DirectoryViewHolder onCreateViewHolder(ViewGroup parent,
                                                  int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_file, parent, false);

        return new DirectoryViewHolder(view, mOnItemClickListener,mOnItemLongClickListener);
    }

    @Override
    public void onBindViewHolder(DirectoryViewHolder holder, int position) {
        File currentFile = mFiles.get(position);

        FileTypeUtils.FileType fileType = FileTypeUtils.getFileType(currentFile);
        holder.mFileImage.setImageResource(fileType.getIcon());
        holder.mFileSubtitle.setText(fileType.getDescription());
        holder.mFileTite.setText(currentFile.getName());
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public File getModel(int index) {
        return mFiles.get(index);
    }
}