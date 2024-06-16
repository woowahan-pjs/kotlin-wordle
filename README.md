# 미션 - 워들

## 🔍 진행 요구 사항
- 미션은 아래의 세 가지 요구 사항으로 구성되어 있고, 각 요구 사항들을 만족하기 위해 노력한다.

### [**1. 진행 요구 사항**](#-진행-방식)

- 미션은 `페어프로그래밍`으로 진행한다.
    > 유스방 5기 - Team 돌쓰
    >   - boradol
    >   - yooth

### [**2. 프로그래밍 요구 사항**](#-프로그래밍-요구-사항)
- 구현 전 환경구성이 프로그래밍 요구 사항을 확인하고 프로그래밍 요구 사항을 목록화한다.
- 구현 중 필요한 프로그래밍 요구사항들을 만족하기 위해 노력한다.
- 제출 전 프로그래밍 요구 사항을 지켰는지 확인한다.
     
### [**3. 기능 요구 사항**](#-기능-요구-사항)
- 기능을 구현하기 전에 **기능 목록**을 만든다.
- **기능 단위로 커밋** 하는 방식으로 진행한다.
- **기능 요구 사항에 기재되지 않은 내용은 스스로 판단하여 구현한다.**
---

## 🎯 프로그래밍 요구 사항

### 환경
- Kotlin 1.9.0에서 실행 가능해야 한다.
  - **Java 코드가 아닌 Kotlin 코드로만 구현해야 한다.**

### 구현 전 확인 사항
- [ ] 프로그램 실행의 시작점은 `Application`의 `main()`이다.
- [ ] `build.gradle.kts` 파일은 변경할 수 없으며, **제공된 라이브러리 이외의 외부 라이브러리는 사용하지 않는다.**
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
- [ ] 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
  - 힌트: MVC 패턴 기반으로 구현한 후, View와 Controller를 제외한 Model에 대한 단위 테스트 추가에 집중한다.

### 제출 전 확인 사항
- [ ] 위의 프로그래밍 요구 사항을 준수했는지 확인한다.
  - 체크박스를 모두 [x]로 만드는 것을 목표로 한다.

---

## 🚀 기능 요구 사항

선풍적인 인기를 끌었던 영어 단어 맞추기 게임이다.

- 6x5 격자를 통해서 5글자 단어를 6번 만에 추측한다.
- 플레이어가 답안을 제출하면 프로그램이 정답과 제출된 단어의 각 알파벳 종류와 위치를 비교해 판별한다.
- 판별 결과는 흰색의 타일이 세 가지 색(초록색/노란색/회색) 중 하나로 바뀌면서 표현된다.
    - 맞는 글자는 초록색, 위치가 틀리면 노란색, 없으면 회색
    - 두 개의 동일한 문자를 입력하고 그중 하나가 회색으로 표시되면 해당 문자 중 하나만 최종 단어에 나타난다.
- 정답과 답안은 `words.txt`에 존재하는 단어여야 한다.
- 정답은 매일 바뀌며 ((현재 날짜 - 2021년 6월 19일) % 배열의 크기) 번째의 단어이다.

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

4/6

⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩
🟩🟩⬜🟩🟩
🟩🟩🟩🟩🟩
```

---

## 🎯 프로그래밍 요구 사항

### 환경
- Kotlin 1.9.0에서 실행 가능해야 한다.
  - **Java 코드가 아닌 Kotlin 코드로만 구현해야 한다.**

### 구현 전 확인 사항
- [ ] 프로그램 실행의 시작점은 `Application`의 `main()`이다.
- [ ] `build.gradle.kts` 파일은 변경할 수 없으며, **제공된 라이브러리 이외의 외부 라이브러리는 사용하지 않는다.**
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
- [ ] 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
  - 힌트: MVC 패턴 기반으로 구현한 후, View와 Controller를 제외한 Model에 대한 단위 테스트 추가에 집중한다.

### 제출 전 확인 사항
- [ ] 위의 프로그래밍 요구 사항을 준수했는지 확인한다.
  - 체크박스를 모두 [x]로 만드는 것을 목표로 한다.
