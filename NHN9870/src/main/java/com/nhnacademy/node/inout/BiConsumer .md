#NHN9870

```java
@FunctionalInterface
public interface BiConsumer<T, U> {
  void accept(T t, U u);

  default BiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> after) {
    Objects.requireNonNull(after);
    return (l, r) -> {
      accept(l, r);
      after.accept(l, r);
    };
  }
}
```

**BiConsumer 인터페이스는 Generic_Type을 2개 사용**<br>
T : 첫 매개변수 타입<br>
U : 둘째 매개변수 타입

*람다* => abstract method 정의 X
- accept() :
- andThen() :

### <예시>
```java
BiConsumer<Integer, Integer> multiply = (a, b) -> {
    int product = a * b;
    System.out.println("Product: " + product);
};

BiConsumer<Integer, Integer> addAndMultiply = addAndPrint.andThen(multiply);

addAndMultiply.accept(5, 3);
```
##### 여러 단계의 동작을 정의, 조합! 으랏차!


