package com.yunwei.map.greedao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "MAP_LAYER15".
 */
public class MapLayer15 {

    private Integer layerType;
    private Integer level;
    private Integer col;
    private Integer row;
    private byte[] layer;

    public MapLayer15() {
    }

    public MapLayer15(Integer layerType, Integer level, Integer col, Integer row, byte[] layer) {
        this.layerType = layerType;
        this.level = level;
        this.col = col;
        this.row = row;
        this.layer = layer;
    }

    public Integer getLayerType() {
        return layerType;
    }

    public void setLayerType(Integer layerType) {
        this.layerType = layerType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public byte[] getLayer() {
        return layer;
    }

    public void setLayer(byte[] layer) {
        this.layer = layer;
    }

}
