# java-ladder

사다리 타기 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

---

## 용어 정의

- 디딤판(step): 사다리 기둥 사이를 연결하는 발판을 의미한다.
- 존재여부(doesExist): 디딤판이 존재하는지를 나타낸다. true이면 디딤판이 있고, false이면 없다.
- 사다리층(layer): 사다리의 각 층을 의미한다.
- 사다리(ladder): 사다리 전체를 의미한다.

## 구현할 기능 목록

### 입력

- [x] 참여할 사람 이름을 입력받는다.
    - [x] 쉼표로 구분한다.
    - [x] 참여할 사람은 두명 이상이어야한다.
    - [x] 이름은 빈값 혹은 공백만을 입력할 수 없다.
    - [x] 이름은 최대 다섯글자까지 입력 가능하다.
    - [x] 이름은 중복될 수 없다.
- [x] 실행 결과를 입력받는다.
    - [x] 쉼표로 구분한다.
    - [x] 실행 결과는 참여할 사람 수만큼 있어야 한다.
- [x] 최대 사다리 높이를 입력받는다.
    - [x] 사다리 높이는 숫자이어야한다.
    - [x] 사다리 높이는 1 이상이어야한다.
- [x] 결과를 보고 싶은 사람을 입력받는다.
    - [x] 쉼표로 구분한다.
    - [x] 참여한 사람이어야 한다.
    - [x] 한 명 이상이어야 한다.
    - [x] all 를 입력하면 전체 참여자의 실행 결과를 출력한다.
    - [x] all or 없음 을 입력하면 실행 결과 출력 후 종료된다.
    - [x] 결과를 보고 싶은 사람을 참여자 중 일부만 입력하면 실행 결과 출력 후 한번 더 입력받는다.
- [x] 사용자가 잘못된 입력을 하면 해당 부분부터 다시 입력받는다.

### 사다리 생성

- [x] 각 사다리층에는 (참여자수 - 1)개 만큼의 디딤판을 생성할 수 있는 공간이 있다.
    - [x] 디딤판 존재여부는 true, false 중 랜덤으로 결정된다.
    - [x] 디딤판이 같은 층에서 연속해서 생성되지 않도록 한다.

### 사다리 출력

- [x] 사람 이름을 5자 기준으로 출력한다.
- [x] 사다리를 출력한다.
- [x] 사다리 아래에 실행 결과를 입력한 순서대로 출력한다.

### 사다리 게임

- [x] 디딤판이 있으면 이동한다.
- [x] 디딤판이 없으면 내려간다.
    - [x] 더이상 내려갈 사다리층이 없으면 종료된다.

### 실행 결과 출력

- [x] 실행 결과를 출력한다.

---

## 부록

### 고민한 포인트

> TDD 연습을 위해 TDD Cycle에 따라 실패하는 테스트, 프로덕션 코드, 리팩토링으로 구분해서 커밋한다.

- 구현 초기에는 TDD 방식은 처음이라 테스트코드 작성, 도메인 로직 작성, 리팩토링 각각 커밋을 진행했습니다.
    - 저희가 구현한 방식이 TDD cycle을 잘 준수했는지 궁금합니다.
- 초반에는 TDD 방식을 제대로 하고 있는지 피드백 받기 위해 각각 커밋했으며 이후 기능 단위로 커밋을 진행했습니다.
- 생성자 테스트는 어떻게 해야할지, 어느 범위까지 해야할지 고민했습니다.
    - 예외 발생 케이스에 대해서만 검증하는 방식으로 진행했습니다.

> DTO 생성을 어디서 해야할까

- DTO 생성 로직이 컨트롤러에 들어가야할지, 모델에 들어가야할지 고민했습니다.
    - 우리의 결론: DTO는 데이터를 전달해주는 역할을 하므로 모델에서 생성해서 컨트롤러에 전달해준다.</br>
    - 위치: Participants의 captureParticipantsName(), Ladder의 captureLayerSteps()

> View에서 어느정도 형태까지 변환해야할까

- InputView에서 입력받은 String으로 그대로줄지, 아니면 기본적인 형태변환까지는 해주어도 될까
    - 우리의 결론: 도메인으로 변환하는것은 안되지만, 모델에 값을 전달하기 위한 최소한의 형태까지는 변경할 수 있다.
- OutputView에서 전달받은 DTO를 순환하거나 값을 가져와서 출력하기 위한 로직이 들어가는게 맞는가
    - 우리의 결론: DTO가 데이터 전달을 위한것이라서 해당 데이터를 출력 형태로 변환하는것까지는 가능하다.

> StepExistenceGenerator 의 generate() 메서드를 static으로 작성

- Layer를 생성할때마다 Random 객체를 새로 생성하는것이 불필요하다는 생각이 들어서 반복되는 로직을 바로 사용할 수 있도록 static으로 작성하였습니다.

> Test만을 위한 getter 메서드가 존재해도 될까

- TDD 방식으로 코드를 구현하다보니 도메인에 getter 메서드를 사용하게 되었습니다.
- 나중에 보니 getter가 테스트 코드에만 사용되고 있었습니다.
    - 우리의 결론: 테스트를 위해 도메인 로직이 변경되는것은 아니니 사용해도 괜찮다고 생각.

> 재귀함수 사용

- 사용자가 입력을 잘못 입력했을때 재입력을 받는 과정을 구현하기 위해 재귀함수를 사용했습니다.
- 재귀함수의 경우 스택오버플로우의 가능성도 있다고 들었는데, 실제 현업에서 사용이 되는 방식인지 궁금합니다.

--- 

### 고민한 포인트 - 2단계 ver

> 테스트를 위해 만든 Ladder 생성자

- layer.move 를 테스트 하려고 했는데, List<Step> 은 랜덤 Step(Boolean) 이 생성되어 추가되는 방식이라 테스트하기 어려웠습니다.
    - 해결한 방법
        - generator 에서 List<Step> 생성 후 Layer 생성자의 파라미터로 받기
        - layer.move 는 해결됐지만, ladder.move 는 여전히 테스트하기 어려움
        - Ladder 의 생성자를 추가
    - 생각나는 다른 방법
        - Layer 를 controller 에서 생성 후 Ladder 의 파라미터로 받기

> Enum 클래스를 만드는 기준

- output에 사용하는 문자열은 view 에서 처리하는데, 이 문자열끼리 관련이 있다면 enum 으로 만들어도 되는건지 궁금합니다.
    - 즉 enum 을 만드는 기준을 잘 모르겠습니다
    - '서로 연관된 상수들의 집합' 이라고 하는데, 서로 연관된 의 기준을 잘 모르겠습니다.
    - OutputView 의 BASE 상수들의 enum 클래스 생성을 고려하라는 리뷰를 보고 고민한 내용입니다
    - Step 의 output "-----" 와 "     " 는 서로 연관된 의 기준을 만족시키지 않는건가요?

> 학습 방법에 대해

- GPT 사용을 적절하게 하고있는지 궁금합니다.
    - 예를 들어, 어떤 기능을 하는 함수가 있을지 궁금해서 구글링을 해보는 과정에서 원하는 결과가 잘 나오지 않거나
      여러 함수 (특히 stream) 를 조합해서 써야한다면 구글링 만으로는 찾기 쉽지 않을 때가 있습니다.
    - 이럴때 gpt 를 사용하면, 이런 함수가 있구나 이렇게 쓸 수 있구나 하며 참고합니다.
    - 이 방식이 괜찮은건지 아니면 조금 더 유용한 학습 방법이 있을지 궁금합니다.
