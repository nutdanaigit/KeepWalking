package com.augmentis.ayp.keepwalking.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Nutdanai on 7/27/2016.
 */
public class KeepWalk {
    private UUID id;
    private String title;
    private Date keepDate;



    public KeepWalk(){UUID.randomUUID();}

    public KeepWalk(UUID uuid){
        this.id=uuid;
        keepDate = new Date();
    }


    public UUID getId() {return id;}
    public String getTitle() {return title;}
    public Date getKeepDate() {return keepDate;}

    public void setId(UUID id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setKeepDate(Date keepDate) {this.keepDate = keepDate;}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UUID=").append(id);
        builder.append(",Title=").append(title);
        builder.append(",Keep Date=").append(keepDate);
        return super.toString();
    }
}
