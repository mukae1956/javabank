package Project.Weekend;

public class Input {
    int money;
    int endPeriod;

    void input() {
        if (money <= 0) {
            throw new MinusException("월 저축액은 0 이상의 값을 입력해 주세요!");
        }
    }

    void input2() {
        if (endPeriod <= 0) {
            throw new Minus2Exception("만기일자는 0 이상의 값을 입력해 주세요!");
        }
    }
}
