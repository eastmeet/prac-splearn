package eastmeet.splearn.domain;

import static java.util.Objects.*;
import static org.springframework.util.Assert.state;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    private Email email;

    private String nickname;

    private String passwordHash;

    private MemberStatus status;

    public static Member create(MemberCreateRequest createReq,
        PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.email = new Email(createReq.email());
        member.nickname = requireNonNull(createReq.nickname());
        member.passwordHash = requireNonNull(
            passwordEncoder.encode(createReq.password()));
        member.status = MemberStatus.PENDING;

        return member;
    }

    public void activate() {
        state(this.status == MemberStatus.PENDING, "PENDING 상태가 아닙니다");

        this.status = MemberStatus.ACTIVE;
    }

    public void deactivate() {
        state(this.status == MemberStatus.ACTIVE, "PENDING 상태가 아닙니다");

        this.status = MemberStatus.DEACTIVATED;
    }

    public boolean verifyPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.passwordHash);
    }

    public void changeNickname(String nickname) {
        this.nickname = requireNonNull(nickname);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.passwordHash = passwordEncoder.encode(requireNonNull(password));
    }

    public boolean isActive() {
        return this.status == MemberStatus.ACTIVE;
    }
}
