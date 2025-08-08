package com.example.folioverse;

import android.app.AlertDialog;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<AdminBook> bookList;

    public BookAdapter(List<AdminBook> list) {
        this.bookList = list;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        AdminBook b = bookList.get(position);

        holder.tvTitle.setText(b.getTitle());
        holder.tvAuthor.setText("by: " + b.getAuthor());
        holder.tvGenre.setText("Genre: " + b.getGenre());
        holder.tvGenre.setText("Descriptipn: " + b.getDescription());

        // Safely load image
        if (b.getCoverImageUri() != null && !b.getCoverImageUri().isEmpty()) {
            try {
                holder.ivCover.setImageURI(Uri.parse(b.getCoverImageUri()));
            } catch (Exception e) {
                holder.ivCover.setImageResource(R.drawable.ic_books);
            }
        } else {
            holder.ivCover.setImageResource(R.drawable.ic_books);
        }


        // Delete button
        holder.btnDelete.setOnClickListener(v -> {
            if (position >= 0 && position < bookList.size()) {
                bookList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, bookList.size());
            }
        });

        // Edit button
        holder.btnEdit.setOnClickListener(v -> {
            View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_add_book, null);
            EditText etTitle = dialogView.findViewById(R.id.etBookTitle);
            EditText etAuthor = dialogView.findViewById(R.id.etBookAuthor);
            EditText etGenre = dialogView.findViewById(R.id.etBookGenre);
            EditText etDescription = dialogView.findViewById(R.id.etBookDescription);

            etTitle.setText(b.getTitle());
            etAuthor.setText(b.getAuthor());
            etGenre.setText(b.getGenre());
            etDescription.setText(b.getDescription());

            ImageView ivPreview = dialogView.findViewById(R.id.ivCoverPreview);
            ivPreview.setImageURI(Uri.parse(b.getCoverImageUri()));


            new AlertDialog.Builder(v.getContext())
                    .setTitle("Edit Book")
                    .setView(dialogView)
                    .setPositiveButton("Update", (dialog, which) -> {
                        b.setTitle(etTitle.getText().toString());
                        b.setAuthor(etAuthor.getText().toString());
                        b.setGenre(etGenre.getText().toString());
                        b.setDescription(etDescription.getText().toString());

                        notifyItemChanged(position);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAuthor, tvGenre;
        Button btnEdit, btnDelete;
        ImageView ivCover;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvBookTitle);
            tvAuthor = itemView.findViewById(R.id.tvBookAuthor);
            tvGenre = itemView.findViewById(R.id.tvBookGenre);
            btnEdit = itemView.findViewById(R.id.btnEditBook);
            btnDelete = itemView.findViewById(R.id.btnDeleteBook);
            ivCover = itemView.findViewById(R.id.ivCoverPreview); // 🔥 this line prevents crash
        }
    }
}
