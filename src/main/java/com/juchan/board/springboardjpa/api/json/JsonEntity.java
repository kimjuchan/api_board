package com.juchan.board.springboardjpa.api.json;


import com.juchan.board.springboardjpa.common.BaseEntitiy;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class JsonEntity extends BaseEntitiy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "json_id")
    private Long id;

    private String msgType;
    private String msg;

    //String 형태로 json 값 전달 받아서 내부 서비스 쪽에서 해당 데이터 가공해서 view에 뿌려줄려고함.
    // (new 테이블에 컬럼을 정의해버리면 추후에 추가 정보에 대해서 컬럼을 수시로 추가해줘야하고 해당 타입별로 필요한 컬럼일 수도 있고 아닌 컬럼일 수 도 있으니 효율적인 관리를 위해서 이렇게 처리해봄)
    private String jsonTypeByMsg;

}
