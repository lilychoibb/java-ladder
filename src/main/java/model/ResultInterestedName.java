package model;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ResultInterestedName {
    private final List<String> resultInterestedName;

    public ResultInterestedName(List<String> resultInterestedName, List<String> participantNames) {
        if (resultInterestedName.equals(List.of("all"))) {
            resultInterestedName = participantNames;
        }
        validateNameValue(resultInterestedName);
        validatePersonName(resultInterestedName, participantNames);
        this.resultInterestedName = Objects.requireNonNull(resultInterestedName);
    }

    private void validatePersonName(List<String> resultInterestedName, List<String> participantNames) {
        long matchCount = resultInterestedName.stream()
                .filter(name -> participantNames.stream().anyMatch(Predicate.isEqual(name)))
                .count();

        if (matchCount < 1) {
            throw new IllegalArgumentException("결과를 보고 싶은 사람은 참여한 사람이어야 한다");
        }
    }

    private void validateNameValue(List<String> resultInterestedName) {
        if (resultInterestedName.isEmpty() || resultInterestedName.contains("\\s+")) {
            throw new IllegalArgumentException("결과를 보고 싶은 사람은 한 명 이상이어야 한다");
        }
    }

    public List<String> getResultInterestedName() {
        return resultInterestedName;
    }
}
