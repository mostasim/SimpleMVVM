package tech.mbsoft.simplemvvm.di;

import javax.inject.Inject;

import dagger.Provides;

public class Test {
    /*public static void main(String[] args) {
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        GreenCoffee greenCoffee = coffeeMaker.greenCoffee;
        RedCoffee redCoffee = coffeeMaker.redCoffee;
        greenCoffee.foo();
        redCoffee.foo();
    }*/

    public class RedCoffee{
        @Inject
        public RedCoffee() {
            System.out.println("RED COFFEE");
        }
        public void foo(){
            System.out.println("RED FOO!!");
        }
    }
    public class GreenCoffee{
        @Inject
        public GreenCoffee() {
            System.out.println("GREEN COFFEE");
        }
        public void foo(){
            System.out.println("GREEN FOO!!");
        }
    }

}

/*class CoffeeMaker{
    @Inject public RedCoffee redCoffee;
    @Inject public GreenCoffee greenCoffee;
    @Provides
    static RedCoffee provideRedCoffe() {
        return new RedCoffee();
    }
    @Provides
    static GreenCoffee provideGreenCoffe() {
        return new GreenCoffee();
    }
}*/
