import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.checklists.entities.CheckList;

class CheckListTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private CheckList checkList;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPACheckList");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		checkList = em.find(CheckList.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		checkList = null;
	}

	@Test
	void test_checkList_basic() {
		assertNotNull(checkList);
		assertEquals("Brush Teeth", checkList.getName());
	}

}
