package com.openclassrooms.mynavdrawer.Controllers.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.mynavdrawer.Controllers.Activities.DetailAnnonce;
import com.openclassrooms.mynavdrawer.Controllers.Fragments.UneAnnonce;
import com.openclassrooms.mynavdrawer.Controllers.Model.Annonce;
import com.openclassrooms.mynavdrawer.R;

import java.util.ArrayList;
import java.util.List;

public class AnnonceAdapter extends RecyclerView.Adapter<AnnonceAdapter.ViewHolder> implements Filterable {
    private List<Annonce> mesAnnonces;
    private List<Annonce> TousMesAnnonces;
    private Context context;
    RequestOptions option;


    public AnnonceAdapter(Context context, List<Annonce> announces) {
        this.mesAnnonces = announces;
        TousMesAnnonces = new ArrayList<>(mesAnnonces);
        this.context = context;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.activity_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        viewHolder.view_container.setOnClickListener(v -> {

            Intent i = new Intent(context, DetailAnnonce.class);
            i.putExtra("id",mesAnnonces.get(viewHolder.getAdapterPosition()).getId());
            i.putExtra("titre",mesAnnonces.get(viewHolder.getAdapterPosition()).getTitre());
            i.putExtra("nomSociete",mesAnnonces.get(viewHolder.getAdapterPosition()).getNomSociete());
            i.putExtra("secteur",mesAnnonces.get(viewHolder.getAdapterPosition()).getSecteur());
            i.putExtra("delai",mesAnnonces.get(viewHolder.getAdapterPosition()).getDelai());
            i.putExtra("description",mesAnnonces.get(viewHolder.getAdapterPosition()).getDescription());
            i.putExtra("lieu",mesAnnonces.get(viewHolder.getAdapterPosition()).getVille());
            i.putExtra("vue",mesAnnonces.get(viewHolder.getAdapterPosition()).getVue());
            i.putExtra("niveau",mesAnnonces.get(viewHolder.getAdapterPosition()).getNiveau());
            i.putExtra("langue",mesAnnonces.get(viewHolder.getAdapterPosition()).getLangue());
            i.putExtra("condition",mesAnnonces.get(viewHolder.getAdapterPosition()).getCondition());
            i.putExtra("contrat",mesAnnonces.get(viewHolder.getAdapterPosition()).getContrat());
            i.putExtra("experience",mesAnnonces.get(viewHolder.getAdapterPosition()).getExperience());
            i.putExtra("formation",mesAnnonces.get(viewHolder.getAdapterPosition()).getFormation());
            i.putExtra("couriel",mesAnnonces.get(viewHolder.getAdapterPosition()).getCouriel());
            i.putExtra("contact",mesAnnonces.get(viewHolder.getAdapterPosition()).getContact());
            i.putExtra("siteweb",mesAnnonces.get(viewHolder.getAdapterPosition()).getSiteweb());
            i.putExtra("telephone",mesAnnonces.get(viewHolder.getAdapterPosition()).getTelephone());
            i.putExtra("o_resum_sms",mesAnnonces.get(viewHolder.getAdapterPosition()).getO_resum_sms());
            i.putExtra("imageUrl", mesAnnonces.get(viewHolder.getAdapterPosition()).getImage());


            context.startActivity(i);

        });
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Annonce annonce = mesAnnonces.get(position);
        // Set item views based on your views and data model

        //loading the image
        Glide.with(context)
                .load(annonce.getImage())
                .into(viewHolder.imageView);

        TextView nomSociete = viewHolder.nomSociete;
        TextView vue = viewHolder.vue;
        TextView contrat = viewHolder.contrat;
        TextView ville = viewHolder.ville;
        TextView niveau = viewHolder.niveau;
        TextView delai = viewHolder.delai;
        TextView titre = viewHolder.titre;


        vue.setText(annonce.getVue());
        contrat.setText(annonce.getContrat());
        ville.setText(annonce.getVille());
        delai.setText(annonce.getDelai());
        //this.vue.setText(vue);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            nomSociete.setText(Html.fromHtml(annonce.getNomSociete(), Html.FROM_HTML_MODE_COMPACT));
            titre.setText(Html.fromHtml(annonce.getTitre(), Html.FROM_HTML_MODE_COMPACT));
            niveau.setText(Html.fromHtml(annonce.getTitre(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            nomSociete.setText(Html.fromHtml(annonce.getNomSociete()));
            titre.setText(Html.fromHtml(annonce.getTitre()));
            niveau.setText(Html.fromHtml(annonce.getTitre()));
        }
    }

    @Override
    public int getItemCount() {
        return mesAnnonces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nomSociete;
        public TextView vue;
        public TextView contrat;
        public TextView ville;
        public TextView niveau;
        public TextView delai;
        public TextView titre;
        LinearLayout view_container;
        ImageView imageView;



        public ViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.activity_item_layout);
            nomSociete = (TextView) itemView.findViewById(R.id.ne_organisme);
            vue = (TextView) itemView.findViewById(R.id.ne_vo_nombre);
            contrat = (TextView) itemView.findViewById(R.id.ne_typeContra);
            ville = (TextView) itemView.findViewById(R.id.ne_lieu);
            niveau = (TextView) itemView.findViewById(R.id.ne_diplome);
            delai = (TextView) itemView.findViewById(R.id.ne_validiteDate);
            titre = (TextView) itemView.findViewById(R.id.ne_titreOffre);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }

    public Annonce getAnnonce(int position){
        return this.mesAnnonces.get(position);
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Annonce> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(TousMesAnnonces);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Annonce item : TousMesAnnonces) {
                    if (item.getSecteur().toLowerCase().contains(filterPattern) || item.getNomSociete().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mesAnnonces.clear();
            mesAnnonces.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}

