package sg.edu.np.week2practical;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.security.DomainCombiner;

import static androidx.core.content.ContextCompat.startActivity;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public final String TAG = "UserViewHolder";
    TextView txt1;
    TextView txt2;
    ImageView image;
    static int id;
    public UserViewHolder (View itemView){
        super(itemView);
        txt1 = itemView.findViewById(R.id.Name);
        txt2 = itemView.findViewById(R.id.description);
        image = itemView.findViewById(R.id.foreground3);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "On Click called");
                Alert();
            }
        });
    }

    private void Alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.image.getContext());
        builder.setTitle("Profile");
        builder.setMessage(String.valueOf(txt2));
        Context context = itemView.getContext();
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG, "On Click called");
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("user index", id);
                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

}
