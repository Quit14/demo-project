package org.demoproject.utill;

public class Demo {
    private String myFirst;
    private String myLast;

    public Demo() {
    }
    public Demo(String a, String b) {
        myFirst=a;
        myLast=b;
    }

    public String getMyFirst() {
        return myFirst;
    }

    public void setMyFirst(String myFirst) {
        this.myFirst = myFirst;
    }

    public String getMyLast() {
        return myLast;
    }

    public void setMyLast(String myLast) {
        this.myLast = myLast;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "myFirst='" + myFirst + '\'' +
                ", myLast='" + myLast + '\'' +
                '}';
    }
}
