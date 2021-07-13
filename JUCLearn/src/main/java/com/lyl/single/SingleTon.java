package com.lyl.single;

public enum SingleTon {
    SINGLE{
        @Override
        public SingleTon getInsatnce() {
            System.out.println("hahah");
            return null;
        }
    };
    public SingleTon getInsatnce(){
        return SINGLE;
    }
}
