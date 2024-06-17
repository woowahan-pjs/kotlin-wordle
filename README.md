# 미션 - 워들

## 🔍 진행 요구 사항
- 미션은 아래의 세 가지 요구 사항으로 구성되어 있고, 각 요구 사항들을 만족하기 위해 노력한다.

### [**1. 진행 요구 사항**](#-기능-요구-사항)

- 미션은 `페어프로그래밍`으로 진행한다.
    > 유스방 5기 - Team 돌쓰
    >   - boradol
    >   - yooth

### [**2. 프로그래밍 요구 사항**](#-프로그래밍-요구-사항)
- 구현 전 환경구성이 프로그래밍 요구 사항을 확인하고 프로그래밍 요구 사항을 목록화한다.
- 구현 중 필요한 프로그래밍 요구사항들을 만족하기 위해 노력한다.
- 제출 전 프로그래밍 요구 사항을 지켰는지 확인한다.
     
### [**3. 기능 요구 사항**](#-기능-요구-사항)
- 기능을 구현하기 전에 기능 요구 사항을 참고하여 **기능 목록**을 만든다.
  - 페어프로그래밍을 위해 **용어를 정리**한다.
  - [**용어 정리**](#-용어-정리)와 [**기능 목록**](#-기능-목록)은 계속 수정될 수 있다.
- **기능 단위로 커밋** 하는 방식으로 진행한다.
- **기능 요구 사항에 기재되지 않은 내용은 스스로 판단하여 구현한다.**
---

## 🎯 프로그래밍 요구 사항

### 환경
- Kotlin 1.9.23에서 실행.
  - **Java 코드가 아닌 Kotlin 코드로만 구현해야 한다.**

### 구현 전 확인 사항
- [x] 프로그램 실행의 시작점은 `Application`의 `main()`이다.
- [ ] `build.gradle.kts` 파일은 변경할 수 없으며, **제공된 라이브러리 이외의 외부 라이브러리는 사용하지 않는다.**
  - `Unknown Kotlin JVM target: 21` 문제로 `kotlin 1.9.23`으로 올림
  - [문제해결](https://github.com/gradle/gradle/issues/25574#issuecomment-1761314551)
- [ ] 프로그램 종료 시 `System.exit()` 또는 `exitProcess()`를 호출하지 않는다.
- [ ] 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 등의 이름을 바꾸거나 이동하지 않는다.

### 구현 중 필요 사항
- [ ] 코틀린 코드 컨벤션[(Kotlin Coding conventions)](https://kotlinlang.org/docs/coding-conventions.html)을 지키면서 프로그래밍한다.
  - Commit 전에 `ktlint`를 습관적으로 확인한다.
    - ```./gradlew ktlintCheck```
    - ```./gradlew addKtlintCheckGitPreCommitHook```
- [ ] indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
- [ ] 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- [ ] 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- [ ] 도메인 로직에 단위 테스트를 구현해야 한다.
  - `JUnit 5`와 `AssertJ`를 이용하여 정리한 기능 목록이 정상적으로 작동하는지 테스트 코드로 확인한다.
    - [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide)
    - [AssertJ User Guide](https://assertj.github.io/doc)
    - [AssertJ Exception Assertions](https://www.baeldung.com/assertj-exception-assertion)
    - [Guide to JUnit 5 Parameterized Tests](https://www.baeldung.com/parameterized-tests-junit-5)
  - 단, UI(System.out, System.in, Scanner) 로직은 제외한다.
- [x] 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
  - 힌트: MVC 패턴 기반으로 구현한 후, View와 Controller를 제외한 Model에 대한 단위 테스트 추가에 집중한다.

### 제출 전 확인 사항
- [ ] 위의 프로그래밍 요구 사항을 준수했는지 확인한다.
  - 체크박스를 모두 [x]로 만드는 것을 목표로 한다.

---

## 🚀 기능 요구 사항

### 워들 게임 규칙

>선풍적인 인기를 끌었던 영어 단어 맞추기 게임이다.
>- 6x5 격자를 통해서 5글자 단어를 6번 만에 추측한다.
>- 플레이어가 답안을 제출하면 프로그램이 정답과 제출된 단어의 각 알파벳 종류와 위치를 비교해 판별한다.
>- 판별 결과는 흰색의 타일이 세 가지 색(초록색/노란색/회색) 중 하나로 바뀌면서 표현된다.
>    - 맞는 글자는 초록색, 위치가 틀리면 노란색, 없으면 회색
>    - 두 개의 동일한 문자를 입력하고 그중 하나가 회색으로 표시되면 해당 문자 중 하나만 최종 단어에 나타난다.
>- 정답과 답안은 `words.txt`에 존재하는 단어여야 한다.
>- 정답은 매일 바뀌며 ((현재 날짜 - 2021년 6월 19일) % 배열의 크기) 번째의 단어이다.

### 입출력 요구 사항

#### 실행 결과 예시

```
WORDLE을 6번 만에 맞춰 보세요.
시도의 결과는 타일의 색 변화로 나타납니다.

정답을 입력해 주세요.
hello

⬜⬜🟨🟩⬜

정답을 입력해 주세요.
label

⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩

정답을 입력해 주세요.
spell

⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩
🟩🟩⬜🟩🟩

정답을 입력해 주세요.
spill

⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩
🟩🟩⬜🟩🟩
🟩🟩🟩🟩🟩

성공!! 정답입니다. 4/6
```

### ✏️ 용어 정리

| 국문          | 영문                | 설명                                                                                                           |
|-------------|-------------------|--------------------------------------------------------------------------------------------------------------|
| 워들 게임       | Wordle Game       | 알파벳 소문자로만 이루어진 5글자 영어 단어를 6번 만에 맞추는 게임이다.                                                                    |
| 단어          | Word              | 워들 게임에서 플레이어가 입력해야 하는 최소 단위이다.                                                                               |
| 사전          | Dictionary        | 유효한 단어들의 목록이다. 이 목록에서 정답 단어를 가져오고 답안 단어의 유효함을 확인한다.                                                          |
| 기준 날짜       | Criterion Date    | 단어 사전에서 한 단어를 가져오는 계산에 필요한 기준 날짜이다.                                                                          |
| 답안 단어       | Answer Word       | 플레이어가 정답이 되는 단어를 맞추기 위해 입력하는 단어이다.                                                                           |
| 오늘의 단어      | Today Word        | 정답이 되는 단어이다. 기준 날짜와 오늘의 날짜를 계산하여 단어 사전에서 단어 하나를 가져온다. 이 단어는 매일 바뀐다.                                          |
| 글자          | Letter            | 답안 단어와 오늘의 단어가 일치하는지 확인하는 최소 단위이다. 글자 여러 개가 모여 단어를 구성한다. 예) "hello"는 'h', 'e', 'l', 'o'의 글자로 이루어져 있다.        |
| 워들 게임 로직    | Wordle Game Logic | 답안 단어와 오늘의 단어를 비교한 단어 결과를 받아 온다.                                                                             |
| 단어 비교기      | Word Comparator   | 답안 단어와 오늘의 단어를 각 글자의 일치를 검증하는 기능을 한다.                                                                        |
| 시도 가능 횟수    | Try Count         | 답안 단어를 입력 가능한 횟수이다. 유효한 단어를 입력할 때마다 1회씩 차감되며, 0회가 되면 게임은 무조건 종료된다.                                           |
| 최대 시도 가능 횟수 | Max Try Count     | 유효한 답안을 입력할 수 있는 최대 횟수이다.                                                                                    |
| 시도한 횟수      | Attempt Count     | 유효한 답안 단어를 입력한 횟수이다. ([최대 시도 가능 횟수] - [시도 가능 횟수])                                                            |
| 단어 결과       | Word Result       | 유효한 답안 단어를 입력 후 해당 답안 단어에 대한 비교를 거친 후 각 글자 일치 상태 목록을 가지고 있다.                                                 |
| 단어 결과 목록    | Word Results      | 글자 일치 상태 목록과 시도 가능 횟수를 가지고 있는 목록이다.                                                                          |
| 글자 일치 상태    | Letter Match      | 답안 단어와 오늘의 단어를 이루는 각 글자들의 일치하는 상태를 나타내는 유형이다.                                                                |
| 완전 일치 글자    | Correct Letter    | 글자 일치 상태 중, 같은 위치에 있는 답안 단어와 오늘의 단어의 글자가 같을 때의 상태이다.                                                         |
| 부분 일치 글자    | Present Letter    | 글자 일치 상태 중, 다른 위치에 있는 글자가 오늘의 단어에 존재하는 상태이다. 이 때, 오늘의 단어에서 한번 비교한 부분 일치 글자는 다음 위치에 같은 부분 일치 글자가 나와도 비교하지 않는다. |
| 불일치 글자      | Absent Letter     | 글자 일치 상태 중, 오늘의 단어에 해당 글자가 없는 상태이다.                                                                          |
| 일치 표시 기호    | Match Marker      | 오늘의 단어 중 답안의 단어와 불일치 상태가 아닌 글자를 표시하는 기호이다.                                                                   |
| 타일          | Tile              | 단어 비교의 각 글자별 결과를 타일의 색깔로 나타낸다. 글자 일치 상태와 대응 된다.                                                              |
| 초록색 타일      | Green Tile        | 글자 완전 일치 상태의 결과를 나타낸 색상의 타일이다.                                                                               |
| 노란색 타일      | Yellow Tile       | 글자 부분 일치 상태의 결과를 나타낸 색상의 타일이다.                                                                               |
| 회색 타일       | Grey Tile         | 글자 불일치 상태의 결과를 나타낸 색상의 타일이다.                                                                                 |
| 성공          | Success           | 워들 게임에서 답안의 단어가 오늘의 단어의 모든 글자가 완전 일치한 상태이다. 시도한 횟수를 알려주며 게임이 종료된다.                                           |
| 실패          | Fail              | 워들 게임을 성공하지 못한 상태에서 시도 가능 횟수가 0회 이하가 되면 워들 게임에 실패한다. 오늘의 단어를 알려주며 게임이 종료된다.                                  |
| 계속          | Continuous        | 워들 게임을 성공하지 못한 상태에서 시도 가능 횟수가 0회보다 크면 게임을 계속 시도해볼 수 있다.                                                      |
| 다시 시도       | Retry             | 유효한 단어를 입력하지 못할 때, 시도 가능 횟수가 차감되지 않는다. 이 때, 유효하지 않은 이유를 안내하며 다시 답안을 입력하게 한다.                                 |
|             |                   |                                                                                                              |


### 💻 기능 목록

#### 출력(Output View)
- [x] 게임 시작 메시지를 출력한다.
  - `WORDLE을 6번 만에 맞춰 보세요.\n시도의 결과는 타일의 색 변화로 나타납니다.`
- [ ] 타일 결과를 출력한다.
  - [ ] 플레이어가 입력한 답안 단어와 비교한 답안 결과에 따라 각 글자의 일치 상태에 맞게 타일을 출력한다.
    - `🟩` 초록색: 오늘의 단어와 비교하여 답안 단어의 글자가 '완전 일치'인 경우
    - `🟨` 노란색: 오늘의 단어와 비교하여 답안 단어의 글자가 '부분 일치'인 경우
    - `⬜️` 회색: 오늘의 단어와 비교하여 답안 단어의 글자가 '불일치'인 경우
  - [ ] 지금까지 입력한 답안 단어에 따른 단어 결과 목록을 출력한다.
- [ ] 다시 시도 할 때, 다시 시도의 메시지와 그 이유를 함께 출력한다.
- [ ] 게임의 결과에 따라 메시지를 출력한다
  - [ ] 성공 메시지를 출력한다. `{시도한 횟수} / {최대 시도 가능 횟수}`도 함께 출력한다.
  - [ ] 실패 메시지를 출력한다. `{오늘의 단어}`를 함께 출력한다.

#### 입력(Input View)
- [x] 답안 단어를 입력받는다. 입력받기 전에 답안 입력을 위한 메시지를 먼저 출력한다.
  - `정답을 입력해 주세요.`

#### 워들 게임(Wordle Game)
- [ ] 단어 결과 목록을 가진다.
- [x] 유효한 답안 단어를 최대 6번 입력받아 게임을 한다.
  - [x] 게임에 아직 성공하지 못한 상태에서 시도 가능 횟수가 0보다 크면 게임을 계속 할 수 있다.
  - [ ] 단어를 입력받아 답안 단어를 만든다.
    - 유효한 답안 단어를 입력 하였을 때,
      - [ ] 게임 검증 로직을 수행한다.
      - [ ] 타일 결과 목록을 출력한다.
      - [ ] 시행 가능 횟수를 1회 차감한다.
    - 유효하지 않은 답안 단어를 입력 하였을 때, 
      - [ ] 답안 단어 입력을 다시 시도해야 한다. 다시 시도 하는 이유를 출력한다.
- [ ] 단어 결과 목록과 대응하는 타일 결과 목록을 출력한다.
  - [ ] 답안 단어 1번 입력하는 경우
  - [ ] 답안 단어를 6번 입력하고 게임에 실패하는 경우
- [ ] 게임이 종료 된다. 게임의 성공과 실패 여부를 판별한다.
  - 게임에 성공 하였을 때
    - [ ] 해당 답안 단어의 단어 결과가 모두 '완전 일치 글자'일 때이다.
      - 관련 메시지 출력
  - 게임에 실패 하였을 때
    - [ ] 게임에 성공하지 않고, 시도횟수가 0회가 될 때이다.

#### 글자(Letter)
- [x] 글자는 하나의 문자를 가진다.
- [x] 글자는 소문자 알파벳이나 일치 표시 기호('#')만 유효하다.
- [x] 일치 표시 기호가 들어간 글자로 변경할 수 있다.

#### 단어(Word)
- [x] 단어는 여러 개의 글자로 이루어진다.
- [x] 단어는 공백만 입력할 수 없다.
- [x] 단어의 길이는 5자 이어야 한다.
- [x] 단어는 사전에 있는 단어이어야 한다.

#### 사전(Dictionary)
- [x] 사전 단어 목록에서 단어 하나를 가져온다.
  - `DictionaryFileLoader`를 이용하여 `words.txt`파일에서 사전의 단어 목록을 불러온다.
- [x] 사전에 단어가 포함되는지 판별한다.
  - 'hello'는 사전에 포함된 단어이다
  - 'abced'는 사전에 포함되지 않은 단어이다.

#### 오늘의 단어(Today Word)
- [x] 오늘의 단어는 현재 날짜를 입력하여 사전에서 한 단어를 불러온다. 이때, 오늘의 단어는 매일 바뀐다.
  - [x] 사전 단어 목록의 ((현재 날짜 - 2021년 6월 19일) % 배열의 크기) 번째의 단어이다.

#### 워들 게임 로직(Wordle Game Logic)
- [x] 오늘의 단어와 답안 단어를 비교하여 단어 결과를 얻는다.
  - 단어 결과 중 글자 일치 상태가 하나만 다른 경우
    - [x] 하나의 글자 결과만 '부분 일치 글자'일 때 비교한다.
    - [x] 하나의 글자 결과만 '완전 일치 글자'일 때 비교한다. - '완전 일치 글자'부터 비교한다
  - 답안 단어에 같은 글자가 있는 경우
    - [x] 두 번 같은 글자가 연속되는 답안 단어에서, 오늘의 단어와 한 글자만 '부분 일치 글자'일 때 비교한다.
    - [x] 두 번 같은 글자가 연속되는 답안 단어에서, 오늘의 단어와 두 글자 '부분 일치 글자'일 때 비교한다.
    - [x] 두 번 같은 글자가 연속되는 답안 단어에서, '부분 일치 글자'와 '완전 일치 글자'를 가질 때 비교한다.
    - [x] 같은 글자가 연속 하지 않는 답안 단어에서, '부분 일치 글자'와 '완전 일치 글자'를 가질 때 비교한다.
    - [x] 세 개의 같은 글자를 가지는 답안 단어에서 하나의 글자 결과만 '부분 일치 글자'일 때 비교한다.
    - [x] 네 개의 글자 결과가 '완전 일치 글자'일 때 비교한다.
  - 단어 결과 중 글자 일치 상태가 모두 같은 경우
    - [x] 모든 글자 결과가 '불일치 상태'일 때 비교한다
    - [x] 모든 글자 결과가 '부분 일치 글자'일 때 비교한다
    - [x] 모든 글자 결과가 '완전 일치 글자'일 때 비교한다

#### 단어 비교기(Word Comparator)
- 단어 비교기는 글자 목록과 단어 결과를 상태로 가진다.
- '완전 일치 글자'로 매칭 시키기
  - [x] 완전 일치 글자를 비교한다. 완전 일치 글자의 상태는 ABSENT -> CORRECT 로 변경된다.
    - [x] 위치와 글자가 같은 경우 체크한다.
- '부분 일치 글자'로 매칭 시키기
  - [x] '완전 일치 글자'매칭 후에 동작한다.
  - [x] 부분 일치 글자를 비교한다. 부분 일치 글자의 상태는 ABSENT -> PRESENT 로 변경된다.
    - [x] 해당 단어 비교기 글자는 '완전 일치 글자'가 아니여야 한다.
    - [x] 각 글자는 다른 위치여야 한다.
    - [x] 비교하려는 답안 단어의 글자는 비교기 글자 목록에 있는 글자여야 한다. 
- [x] '완전 일치 글자'거나 '부분 일치 글자'이면 해당 단어 비교기 글자에 일치 표시 기호('#')로 변경된다.

#### 시도 가능 횟수(Try Count)
- [ ] 시도 가능 회수는 횟수를 가진다. 최초 생성할 때에는 6회이다.
- [ ] 시도 가능 횟수는 0회보다 커야 한다.
- [ ] 시도 가능 횟수를 1회씩 차감할 수 있다.
- [ ] 시도 가능 횟수가 남았는지 알 수 있다.
- [ ] 시도한 횟수를 구할 수 있다.

#### 단어 결과(Word Result)
- [x] 5개의 글자 일치 상태 목록으로 이루어져 있다.
  - 워들 게임 로직의 결과를 관리한다.
- [x] 처음에 생성할 때, 글자 일치 상태는 불일치 상태이다.
- [x] 해당 글자의 글자 일치 상태를 변경할 수 있다.
- [x] 해당 글자가 '완전 일치 상태'인지 확인한다.
- [ ] 모든 글자 일치 상태가 '완전 일치 상태'이면, 해당 단어는 오늘의 단어와 같다.(게임 성공 여부가 된다)

#### 단어 결과 목록(Word Results)
- [ ] 단어결과와 시도횟수를 가진다. 이때, 단어결과 목록은 빈 목록이고, 시도한 횟수는 0이다.
- [ ] 단어 결과를 추가하면, 시도횟수는 1회 증가한다.
- [ ] 단어 결과 목록에서 단어 결과가 성공인 상태가 추가되면 게임은 성공한다.
- [ ] 단어 결과 목록에서 아직 게임 성공하지 못하고 시도 가능 횟수가 1 이상이면 게임을 계속할 수 있다. 
