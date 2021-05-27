package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    // static이어야 딱 하나만 생성된다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 싱글턴으로 생성한다.
    // private으로 선언해 아무도 새로 생성하지 못하게 한다.
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // store의 모든 values를 꺼내서 기존 리스트를 건드리지 않고 새로운 리스트로 반환한다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
