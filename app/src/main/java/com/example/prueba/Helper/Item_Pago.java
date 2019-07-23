package com.example.prueba.Helper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.prueba.R;
import com.example.prueba.RedFragment;


import java.util.ArrayList;

public class Item_Pago extends BaseAdapter {
    Context context ;
    ArrayList pagos;

    public Item_Pago(Fragment context, ArrayList<Pago> pagos_arr){
        this.context= context.getContext();
        this.pagos=pagos_arr;
    }

    @Override
    public int getCount() {
        return pagos.size();
    }

    @Override
    public Object getItem(int position) {
        return pagos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pagos.indexOf(getItem(position));
    }

    private  class ViewHolder
    {
        TextView codigo_credito;
        TextView nombre_completo;
        TextView valor_cuota;
        TextView saldo_capital;
    }


    @Override
    public View getView(int position, View convertview, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertview == null) {
            convertview = mInflater.inflate(R.layout.item_pago_2, null);
            holder = new ViewHolder();

            holder.codigo_credito = (TextView) convertview.findViewById(R.id.codigo_credito);
            holder.nombre_completo=(TextView) convertview.findViewById(R.id.nombre_completo);
            holder.valor_cuota=(TextView) convertview.findViewById(R.id.valor_cuota);
            holder.saldo_capital=(TextView) convertview.findViewById(R.id.saldo_capital);
            convertview.setTag(holder);
        }else
            holder = (ViewHolder) convertview.getTag();
        Pago item= (Pago) getItem(position);

        holder.codigo_credito.setText(item.getCodigo_Credito());
        holder.nombre_completo.setText(item.getNombre_Completo());
        holder.valor_cuota.setText(item.getValor_Cuota().toString());
        holder.saldo_capital.setText(item.getSaldo_Capital().toString());

        return convertview;
    }
}
