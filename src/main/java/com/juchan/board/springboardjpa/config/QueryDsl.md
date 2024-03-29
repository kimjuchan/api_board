
검색 기능 구현 관련해서 QueryDsl을 선택한 이유 

    쿼리 메소드:
    
    장점:
    인터페이스의 메소드 시그니처만으로 쿼리를 정의할 수 있어 간편하고 직관적입니다.
    메소드 이름 자체가 쿼리의 의도를 표현하므로 가독성이 좋습니다.
    IDE의 자동 완성 및 오타 검사 기능을 활용할 수 있어 개발 생산성이 높습니다.
    
    단점:
    복잡한 쿼리를 표현하기 어렵습니다. 단순한 검색이나 정렬에 적합하지만, 복잡한 조인이나 집계 함수 등은 표현하기 어렵습니다.
    쿼리 메소드의 기능이 한정되어 있어, 일부 고급 쿼리 작업을 수행하기 어렵거나 불가능할 수 있습니다.
    
    JPQL(Java Persistence Query Language):
    장점:
    객체 지향적인 접근 방식으로 쿼리를 작성할 수 있어 객체 간의 관계를 쉽게 표현할 수 있습니다.
    복잡한 쿼리를 작성할 수 있어 유연성이 높습니다.
    데이터베이스에 독립적으로 동작하므로 데이터베이스 변경에 따른 영향을 최소화할 수 있습니다.

    단점:
    쿼리 작성 시 오타나 문법 오류를 빠르게 발견하기 어렵습니다.
    쿼리가 문자열 형태로 작성되기 때문에 컴파일 타임에 오류를 찾을 수 없고, 런타임 시에 오류가 발생할 수 있습니다.
    JPQL은 정적인 문자열이기 때문에 IDE의 지원이 제한적일 수 있습니다.
    
    QueryDSL(Query Domain Specific Language):
    장점:
    타입 안정성을 제공하여 컴파일 시점에 오류를 발견할 수 있습니다.
    자바 코드로 쿼리를 작성하기 때문에 IDE의 지원을 최대한 활용할 수 있습니다.
    복잡한 쿼리를 작성하기 쉽고, 유지보수가 용이합니다.

    단점:
    학습 곡선이 높을 수 있습니다. 처음에는 익숙하지 않을 수 있지만, 익숙해지면 효율적으로 사용할 수 있습니다.
    동적인 쿼리 작성이 복잡할 수 있습니다. 하지만 동적인 쿼리를 작성할 때도 유연하고 강력한 기능을 제공합니다.

-> 결론 : 컴파일 시 에러 발생 지점 잡으면서도 가장 편리하고 무난하게 사용할 수 있는게 QueryDsl이라고 생각하게됨