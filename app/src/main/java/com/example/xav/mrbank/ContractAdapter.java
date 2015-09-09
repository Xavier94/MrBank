package com.example.xav.mrbank;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContractAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    ArrayList<ListItem> list_contract = new ArrayList<>();
    Context context;

    // on passe le context afin d'obtenir un LayoutInflater pour utiliser notre
    // row_layout.xml
    // on passe les valeurs de notre à l'adapter
    public ContractAdapter(Context context, ArrayList<ListItem> myList) {
        this.list_contract = myList;
        this.context = context;
    }

    /**
     * Return size of list.
     * @return int
     */
    @Override
    public int getCount() {
        return list_contract.size();
    }

    /**
     * Return element of list at position
     * @param position Position in list
     * @return ListItem (Object)
     */
    @Override
    public ListItem getItem(int position) {
        return list_contract.get(position);
    }

    /**
     * Return id element of list at position.
     * @param position Position in list
     * @return long
     */
    @Override
    public long getItemId(int position) {
        return list_contract.indexOf(getItem(position));
    }

    /**
     * Return view at position.
     * @param position int
     * @param convertView view
     * @param parent Parent
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder mViewHolder = null;

        // au premier appel ConvertView est null, on inflate notre layout
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.contract_row_list, parent, false);

            // nous plaçons dans notre MyViewHolder les vues de notre layout
            mViewHolder = new MyViewHolder();
            mViewHolder.layoutList = (RelativeLayout) convertView.findViewById(R.id.layoutList);
            mViewHolder.imageViewIconMoney = (ImageView) convertView.findViewById(R.id.imageViewIconMoney);
            mViewHolder.textViewStatus = (TextView) convertView.findViewById(R.id.textViewStatus);
            mViewHolder.textViewTimer = (TextView) convertView.findViewById(R.id.textViewTimer);
            mViewHolder.textViewMoney = (TextView) convertView.findViewById(R.id.textViewMoney);
            //mViewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

            // nous attribuons comme tag notre MyViewHolder à convertView
            convertView.setTag(mViewHolder);
        } else {
            // convertView n'est pas null, nous récupérons notre objet MyViewHolder
            // et évitons ainsi de devoir retrouver les vues à chaque appel de getView
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        // nous récupérons l'item de la liste demandé par getView
        ListItem listItem = getItem(position);

        // nous pouvons attribuer à nos vues les valeurs de l'élément de la liste
        // New
        if (listItem.getStatus() == 0) {
            mViewHolder.layoutList.setBackgroundColor(0xf5f5f5);
            mViewHolder.imageViewIconMoney.setImageResource(R.drawable.bg_sac_actif);
            mViewHolder.textViewStatus.setText(R.string.list_contract_status_new_contract);
            //mViewHolder.textViewStatus.setTextColor(0xa500eb);
            mViewHolder.textViewTimer.setText(String.valueOf(listItem.getTimeLeft()));
            mViewHolder.textViewMoney.setText(R.string.list_contract_status_new);
        }
        // Current
        else if (listItem.getStatus() == 1) {
            mViewHolder.layoutList.setBackgroundColor(0xfff);
            mViewHolder.imageViewIconMoney.setImageResource(R.drawable.bg_sac_inactif);
            mViewHolder.textViewStatus.setText("Contrat en cours");
            //mViewHolder.textViewStatus.setTextColor(0x787878);
            mViewHolder.textViewTimer.setText(String.valueOf(listItem.getTimeLeft()));
            mViewHolder.textViewMoney.setText(String.valueOf(listItem.getMoney()) + "€");
        }
        // Win
        else if (listItem.getStatus() == 2) {
            mViewHolder.layoutList.setBackgroundColor(0xfff);
            mViewHolder.imageViewIconMoney.setImageResource(R.drawable.bg_sac_inactif);
            mViewHolder.textViewStatus.setText("Contrat gagné");
            //mViewHolder.textViewStatus.setTextColor(0x787878);
            mViewHolder.textViewTimer.setText(String.valueOf(listItem.getTimeLeft()));
            mViewHolder.textViewMoney.setText(String.valueOf(listItem.getMoney()) + "€");
        }
        // Lost
        else if (listItem.getStatus() == 3) {
            mViewHolder.layoutList.setBackgroundColor(0xfff);
            mViewHolder.imageViewIconMoney.setImageResource(R.drawable.bg_sac_inactif);
            mViewHolder.textViewStatus.setText("Contrat perdu");
            //mViewHolder.textViewStatus.setTextColor(0x787878);
            mViewHolder.textViewTimer.setText(String.valueOf(listItem.getTimeLeft()));
            mViewHolder.textViewMoney.setText(String.valueOf(listItem.getMoney()) + "€");
        }
        // Judgement
        else {
            mViewHolder.layoutList.setBackgroundColor(0xfff);
            mViewHolder.imageViewIconMoney.setImageResource(R.drawable.bg_sac_inactif);
            mViewHolder.textViewStatus.setText("Demande dejugement");
            //mViewHolder.textViewStatus.setTextColor(0x787878);
            mViewHolder.textViewTimer.setText(String.valueOf(listItem.getTimeLeft()));
            mViewHolder.textViewMoney.setText(String.valueOf(listItem.getMoney()) + "€");
        }

        //mViewHolder.imageView.setImageResource(listItem.getImageId());

        // nous retournons la vue de l'item demandé
        return convertView;
    }

    /**
     * MyViewHolder cache view for adapter
     */
    static class MyViewHolder {
        RelativeLayout layoutList;
        ImageView imageViewIconMoney;
        TextView textViewStatus;
        TextView textViewTimer;
        TextView textViewMoney;
    }

    // nous affichons un Toast à chaque clic sur un item de la liste
    // nous récupérons l'objet grâce à sa position
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(context, "Item " + (position + 1) + ": " + this.list_contract.get(position), Toast.LENGTH_SHORT);
        toast.show();
    }
}
