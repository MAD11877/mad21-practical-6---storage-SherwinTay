package sg.edu.np.week2practical;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{

    ArrayList<User> data;

    //Passing in data
    public UserAdapter (ArrayList<User> input){
        data = input;
    }


    //creating layout
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item;
        if (getItemViewType(viewType) == 0 ){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false);
        }

        else{
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.endwith7layout, parent, false);
        }


        return new UserViewHolder(item);
    }


    //attaching data to layout
    public void onBindViewHolder(UserViewHolder holder, int position){
        User u = data.get(position);
        holder.txt1.setText(u.name);
        holder.txt2.setText(u.description);
        holder.image.setOnClickListener(null);
        holder.id = position;
    }

    public int getItemCount(){return data.size();}

    private ArrayList<User> checklastdigit(ArrayList<User> UserList){
        ArrayList<User> endwith7list = new ArrayList<>();
        for (int i =0; i < UserList.size(); i++) {
            if (UserList.get(i).name.endsWith("7")){
                endwith7list.add(UserList.get(i));
            }
        }

        return endwith7list;
    }

    @Override
    public int getItemViewType(int position){
        if (data.get(position).name.endsWith("7")){
            return position;
        }
        else{
            return 0;
        }
    }
}
