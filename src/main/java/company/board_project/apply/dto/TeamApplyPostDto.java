package company.board_project.apply.dto;

import company.board_project.constant.AcceptType;
import company.board_project.constant.AgeType;
import company.board_project.constant.LevelType;
import company.board_project.constant.MatchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamApplyPostDto {
    private int age;
    private String applierName;
    private String applyMessage;
    private LevelType levelType;
    private AcceptType acceptType;

    @Override
    public String toString() {
        return "TeamApplyPostDto{" +
                ", age=" + age +
                ", applierName='" + applierName + '\'' +
                ", applyMessage='" + applyMessage + '\'' +
                ", levelType=" + levelType +
                ", acceptType=" + acceptType +
                '}';
    }
}
