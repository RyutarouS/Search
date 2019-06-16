public class Foo {
    private final Hoge hoge;


    public Foo(Hoge hoge) {
        this.hoge = hoge;
    }

    public String getHoge() {
        return hoge.getName(1);
    }
}
