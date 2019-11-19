package dev.mcdonaldkiosk.page;

public class PageData {

    private final KioskPageType nextPageType;
    private final KioskPageType previousPageType;

    /**
     * 생성자 파라미터로 빌더를 받아 객체를 생성한다.
     *
     * @param builder
     */
    private PageData(Builder builder) {
        nextPageType = builder.nextPageType;
        previousPageType = builder.previousPageType;
    }

    KioskPageType getNextPageType() {
        return nextPageType;
    }

    KioskPageType getPreviousPageType() {
        return previousPageType;
    }


    /**
     * 빌더 패턴을 적용
     * 참조 : https://johngrib.github.io/wiki/builder-pattern/
     * - 각 인자가 어떤 의미인지 알기 쉽다.
     * - setter 메소드가 없으므로 변경 불가능 객체를 만들 수 있다.
     * - 한 번에 객체를 생성하므로 객체 일관성이 깨지지 않는다.
     * - build() 함수가 잘못된 값이 입력되었는지 검증하게 할 수도 있다.
     */
    public static class Builder {

        private KioskPageType nextPageType = KioskPageType.EMPTY_PAGE;
        private KioskPageType previousPageType = KioskPageType.EMPTY_PAGE;

        public Builder() {
        }

        public Builder nextPageType(KioskPageType pageType) {
            this.nextPageType = pageType;
            return this;
        }

        public Builder previousPageType(KioskPageType pageType) {
            this.previousPageType = pageType;
            return this;
        }

        public PageData build() {
            return new PageData(this);
        }
    }
}
