package com.easyticket.et.zxy.adpter;

import
        android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.easyticket.et.zxy.View.XRecyclerView;
import  com.easyticket.et.zxy.data.AppStore.*;
import com.bumptech.glide.Glide;
import com.easyticket.et.zxy.R;
import com.easyticket.et.zxy.activity.TicketActiveActivity;
import com.easyticket.et.zxy.util.LogHandle;

import java.util.ArrayList;
import java.util.List;



public class TicketAdapter extends XRecyclerView.Adapter<TicketAdapter.ViewHolder>{

    private List<Ticket> mTicketList;
    private Context context;
    private int type;
    private int layoutId;
    static   class  ticketType {
     static  final int
        SUBWAY=2,          //地铁门票
        MINE_TICKET=3,     //已经购买的门票
        ALL_SUBWAY_TICKET=6, //所有地铁门票，已交易完成的
        NO_PAY_TICKET=4,   //未支付的门票
        ALL_FINSHED_TICKET=5,//已交易完成的门票
        SIGHT=1,           //景点，供浏览的门票
        REFUND=7,           //申请退款的门票
        SIGHT_TICKET=8;     //景点的门票
    };

    static class ViewHolder extends XRecyclerView.ViewHolder {
        View  ticketView;
        TextView  ticket_price;
        TextView ticket_sale_number;
        TextView ticket_name;
        TextView ticket_num;
        TextView ticket_dealLine_value,ticket_dealLine_view;
        TextView ticket_grade;
        TextView ticket_locate;
        TextView ticket_details;
        TextView ticket_evaluate;
        TextView ticket_briefIntroduction;
        Button applyToRefund;
        Button share;
        Button ticket_pay;
        TextView original_destination_place;
        List<TextView> ticket_labels;
        TextView applyRefundResult;
        ImageView ticket_picture;

        public ViewHolder(View view) {
            super(view);
            ticketView = view;
            share = (Button)view.findViewById(R.id.share_ticket);
        //    applyRefundResult = (TextView)view.findViewById(R.id.refund_reuslt);
            applyToRefund = (Button)view.findViewById(R.id.apply_refund);
            original_destination_place = (TextView)view.findViewById(R.id.original_destination_place);
            ticket_evaluate = (TextView)view.findViewById(R.id.evaluate);
            ticket_num = (TextView) view.findViewById(R.id.ticket_num);
            ticket_pay = (Button)view.findViewById(R.id.topay);
            ticket_price = (TextView)view.findViewById(R.id.ticket_price);
            ticket_name = (TextView)view.findViewById(R.id.ticket_name);
            ticket_picture = (ImageView)view.findViewById(R.id.ticket_picture);
            ticket_sale_number = (TextView)view.findViewById(R.id.ticket_sales_num);
            ticket_locate = (TextView)view.findViewById(R.id.ticket_locate);
            ticket_briefIntroduction =(TextView)view.findViewById(R.id.ticket_briefIntroduction);
            ticket_grade = (TextView)view.findViewById(R.id.ticket_grade);
            ticket_details = (TextView)view.findViewById(R.id.ticket_detail);
            ticket_dealLine_value = (TextView)view.findViewById(R.id.ticket_dealLine_value);
            ticket_dealLine_view = (TextView)view.findViewById(R.id.ticket_dealLine_view);
            ticket_labels = new ArrayList<TextView>();
            ticket_labels.add((TextView)view.findViewById(R.id.ticket_Label_one));
            ticket_labels.add((TextView)view.findViewById(R.id.ticket_Label_two));
            ticket_labels.add((TextView)view.findViewById(R.id.ticket_Label_three));
            ticket_labels.add((TextView)view.findViewById(R.id.ticket_Label_four));
        }
    }

    public TicketAdapter(List<Ticket> ticketList, Context context,int type,int layoutId) {
        this.context = context;
        mTicketList = ticketList;
        this.type = type;
        this.layoutId = layoutId;
    }

    public static void actionStart(Context context,Class activity,List<String> params, List<String> datas){
        Intent intent = new Intent(context,activity);

      int p=0;
       if(params!=null && datas!=null)
        for(String data:datas)
            intent.putExtra(params.get(p++),data);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.ticketView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Ticket ticket = mTicketList.get(position);
                switch (type) {
                    case ticketType.MINE_TICKET:
                        List<String> parmas = new ArrayList<>();
                        List<String> datas = new ArrayList<>();
                        parmas.add("ticketCode");
                        parmas.add("areaid");
                        parmas.add("gatesNum");
                        parmas.add("ticketType");
                        parmas.add("areaid");

                        datas.add(ticket.getTicketCode());
                        datas.add(ticket.getAreaId());
                        datas.add(ticket.getGatesNum()+"");
                        datas.add(ticket.getTicketType()+"");
                        datas.add(ticket.getAreaId());
                    actionStart(context, TicketActiveActivity.class,parmas,datas);
                     break;
                     case ticketType.NO_PAY_TICKET:
                         break;
                }
            }
        });
        if(holder.ticket_details!=null)
        holder.ticket_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Ticket ticket = mTicketList.get(position);

            }
        });

        if(holder.ticket_pay!=null)
        holder.ticket_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Ticket ticket = mTicketList.get(position);

            }
        });

        if(holder.ticket_evaluate!=null)
        holder.ticket_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Ticket ticket = mTicketList.get(position);

            }
        });

        if(holder.applyToRefund!=null)
        holder.applyToRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Ticket ticket = mTicketList.get(position);

            }
        });

        if(holder.share!=null)
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Ticket ticket = mTicketList.get(position);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
          switch(type){
              case ticketType.ALL_SUBWAY_TICKET:
                  init_all_Subwayticket(position,holder);
                  break;
              case ticketType.ALL_FINSHED_TICKET:
                  init_all_FinshedTicket(position,holder);
                  break;
              case ticketType.MINE_TICKET:
                  init_mine_ticket(position,holder);
                  break;
              case ticketType.NO_PAY_TICKET:
                  init_nopay_ticket(position,holder);
                  break;
              case ticketType.REFUND:
                  init_refundTicket(position,holder);
                  break;
              case ticketType.SIGHT:
                  init_sight(position,holder);
                  break;
              case ticketType.SIGHT_TICKET:
                  //......
                  //......
                  break;
              case ticketType.SUBWAY:
                  init_subway_ticket(position,holder);
                  break;
          }
    }

    @Override

    public int getItemCount() {
        return mTicketList.size();
    }

    public void init_sight(int position,ViewHolder holder){   //供浏览的景点门票
        Ticket ticket = mTicketList.get(position);
        String[] texts =   ticket.getLabels().split(".");
        int i = 0;
        int p=0;
        while(i<texts.length) {
            holder.ticket_labels.get(p++).setText(texts[i++]);
        }
        Glide.with(context).load(ticket.getImagePath()).into(holder.ticket_picture);
        holder.ticket_name.setText(ticket.getName());
        holder.ticket_grade.setText(String.valueOf(ticket.getGrade()));
        holder.ticket_briefIntroduction.setText(ticket.getBriefIntroduction());
        holder.ticket_sale_number.setText(ticket.getSales_number());
        holder.ticket_price.setText(String.valueOf(ticket.getPrice()));
        holder.ticket_locate.setText(ticket.getLocate());
    }




    public void init_subway_ticket(int position,ViewHolder holder){
        Ticket ticket = mTicketList.get(position);
        Glide.with(context).load(ticket.getImagePath()).into(holder.ticket_picture);
        holder.ticket_name.setText(ticket.getName());
        holder.ticket_dealLine_view.setText(R.string.dealLine);
        holder.ticket_dealLine_value.setText(ticket.getDealLine());
        holder.ticket_price.setText(String.valueOf(ticket.getPrice()));
        holder.ticket_num.setText(ticket.getNum());
        holder.original_destination_place.setText(ticket.getOriginal_destination_place());
    }

    public void init_refundTicket(int position,ViewHolder holder){
        Ticket ticket = mTicketList.get(position);
        Glide.with(context).load(ticket.getImagePath()).into(holder.ticket_picture);
        holder.ticket_name.setText(ticket.getName());
        holder.ticket_price.setText(String.valueOf(ticket.getPrice()));
        holder.ticket_num.setText(ticket.getNum());
       // holder.applyRefundResult.setText(ticket.getRefundResult());
    }


    public void init_other_ticket(int position,ViewHolder holder){


    }

    public void init_mine_ticket(int position,ViewHolder holder){
        Ticket ticket = mTicketList.get(position);
      //  if(XRecyclerView.getStatus()!=0)

        Glide.with(context).load(R.drawable.s2).into(holder.ticket_picture);

        holder.ticket_name.setText(ticket.getName());

        holder.ticket_price.setText(String.valueOf(ticket.getAreaId()));
        holder.ticket_num.setText(String.valueOf(ticket.getNum()));

        holder.ticket_dealLine_view.setText(R.string.dealLine);
        holder.ticket_dealLine_value.setText(ticket.getDealLine());

    }

    public void init_nopay_ticket(int position,ViewHolder holder){
        Ticket ticket = mTicketList.get(position);
        Glide.with(context).load(ticket.getImagePath()).into(holder.ticket_picture);
        holder.ticket_name.setText(ticket.getName());
        holder.ticket_sale_number.setText(ticket.getSales_number());
        holder.ticket_price.setText(String.valueOf(ticket.getPrice()));
        holder.ticket_num.setText(ticket.getNum());
    }


    public void init_all_Subwayticket(int position,ViewHolder holder){

    }
    public void init_all_FinshedTicket(int position,ViewHolder holder){   //交易完成的门票
        Ticket ticket = mTicketList.get(position);
        Glide.with(context).load(ticket.getImagePath()).into(holder.ticket_picture);
        holder.ticket_name.setText(ticket.getName());
        holder.ticket_price.setText(String.valueOf(ticket.getPrice()));
        holder.ticket_num.setText(ticket.getNum());
    }


}