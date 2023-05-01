public class MyTestingClass {
    private int id;
    private static int gen_id = 1;
    private String fname;
    private String lname;

    public MyTestingClass(String fname, String lname) {
        this.id = gen_id++;
    }

    @Override
    public int hashCode() {
        return (fname.hashCode() + lname.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        MyTestingClass item = (MyTestingClass) obj;
        return item.fname.equals(this.fname) && item.lname.equals(this.lname);
    }
}
