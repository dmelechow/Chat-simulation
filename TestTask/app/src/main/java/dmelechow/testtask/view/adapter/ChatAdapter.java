package dmelechow.testtask.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import dmelechow.testtask.R;
import dmelechow.testtask.model.Chat;

/**
 * Created by a1 on 7/23/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.AbstractViewHolder> {

    private Context context;
    private ArrayList<Chat> items = new ArrayList<>();

    public ChatAdapter(Context context) {
        this.context = context;
    }

    public void updateChat(ArrayList<Chat> chats) {
        this.items.clear();
        this.items.addAll(chats);
        this.notifyDataSetChanged();
    }

    @Override
    public AbstractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_chat, parent, false);
        return new ChatHolder(viewInflate);

    }

    @Override
    public void onBindViewHolder(AbstractViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        return 0;
    }

    protected abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder {

        protected CircleImageView iconAva;
        protected ImageView unRead;
        protected TextView name;
        protected TextView message;
        protected TextView date;
        protected View itemView;


        public AbstractViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            name = itemView.findViewById(R.id.name);
            message = itemView.findViewById(R.id.message);
            date = itemView.findViewById(R.id.date);
            iconAva = itemView.findViewById(R.id.icon_ava);
            unRead = itemView.findViewById(R.id.icon_unread);
        }

        public abstract void bind(T item);
    }


    protected class ChatHolder extends AbstractViewHolder<Chat> {

        public ChatHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(Chat chat) {
            if (!TextUtils.isEmpty(chat.getName())) {
                name.setText(chat.getName());
            }

            if (!TextUtils.isEmpty(chat.getIconChat())) {
                Glide.with(context)
                        .load(chat.getIconChat())
                        .into(iconAva);
            }

            if (chat.getLastMessage() != null) {
                if (!TextUtils.isEmpty(chat.getLastMessage().getText())) {
                    message.setText(chat.getLastMessage().getText());
                }

                if (!TextUtils.isEmpty(chat.getLastMessage().getDate())) {
                    date.setText(chat.getLastMessage().getDate());
                }

                if(chat.getLastMessage().isUnread()){
                    unRead.setImageDrawable(context.getResources().getDrawable(R.drawable.marker_unread));
                } else {
                    unRead.setImageDrawable(context.getResources().getDrawable(R.drawable.marker_read));
                }
            }
        }
    }
}
