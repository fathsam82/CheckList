import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.skilldistillery.checklists.entities.CheckListType;

class CheckListTypeTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private CheckListType checkListType;

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
		checkListType = em.find(CheckListType.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		checkListType = null;
	}

	@Test
	void test_checkListType_basic() {
		assertNotNull(checkListType);
		assertEquals("Personal Hygiene", checkListType.getName());
		
	}
	
	

}
