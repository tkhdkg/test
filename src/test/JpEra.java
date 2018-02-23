package test;

import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.time.chrono.Era;
import java.time.chrono.JapaneseDate;
import java.time.chrono.JapaneseEra;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

public class JpEra {

	
	public static void main(String[] args) {
		
		// Japanese Era���t���N�V�����Ő����i�V�N���̒萔�ɂ�������́j
//		JapaneseEra MITEI = new JapaneseEra(3, LocalDate.of(2019, 4, 1));
		Class<JapaneseEra> clazz = JapaneseEra.class;
		Class<?>[] types = { int.class, LocalDate.class };
		Constructor<JapaneseEra> constructor;
		Object[] argsEra = { Integer.valueOf(3), LocalDate.of(2019, 4, 1) };
		JapaneseEra mitei;
		try {
			constructor = clazz.getConstructor(types);
			mitei = constructor.newInstance(argsEra);
		} catch (IllegalArgumentException |
		         ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
		
		// �V�N���̃V���A���l
		System.out.println("0:" + (new Date("2019/04/01 00:00:00").getTime()));
		
		// �����̐���
		JapaneseDate japaneseDate = JapaneseDate.of(2019, 4, 2);

		// �a���̐���(japaneseDate�Ɠ���)
		//JapaneseDate fromWareki = JapaneseDate.of(JapaneseEra.HEISEI, 31, 4, 1);
		JapaneseDate fromWareki = JapaneseDate.of(mitei, 31, 4, 1);
		System.out.println("1:" + fromWareki.toString());

		// �����̎擾
		Era era = japaneseDate.getEra();
		System.out.println("2:" + era.toString());

		// �����̔N�̎擾
		int yearOfEra = japaneseDate.get(ChronoField.YEAR_OF_ERA);
		System.out.println("3:" + yearOfEra);

		// �����28�N��ƕ\��
		DateTimeFormatter kanjiFormatter = DateTimeFormatter.ofPattern("Gyy�N");
		String kanjiFormatted = kanjiFormatter.format(japaneseDate);
		System.out.println("4:" + kanjiFormatted);

		// �H.28��ƕ\��
		DateTimeFormatter alphabetFormatter = DateTimeFormatter.ofPattern("GGGGG.yy");
		String alphabetFormatted = alphabetFormatter.format(japaneseDate);
		System.out.println("5:" + alphabetFormatted);
	}

}
