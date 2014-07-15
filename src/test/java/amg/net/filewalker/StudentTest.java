package amg.net.filewalker;

import java.io.File;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import amg.net.filewalker.student.Mark;
import amg.net.filewalker.student.Student;

public class StudentTest {

	@Test
	public void testCreatingObjectFromXml() throws JAXBException {
		String path = "src/main/resources/Student.xml";
		JAXBContext jc = JAXBContext.newInstance(Student.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		StreamSource streamSource = new StreamSource(new File(path));
		JAXBElement<Student> je = unmarshaller.unmarshal(streamSource,
				Student.class);

		Student student = new Student();
		student.setLastName("Farek");
		student.getName().add("Marek");
		Mark mark = new Mark();
		mark.setMarkName("Polish");
		mark.setMarkValue(5);
		student.getMarks().add(mark);

	}

}
