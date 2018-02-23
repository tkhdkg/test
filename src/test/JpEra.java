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
		
		// Japanese Eraリフレクションで生成（新年号の定数にあたるもの）
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
		
		// 新年号のシリアル値
		System.out.println("0:" + (new Date("2019/04/01 00:00:00").getTime()));
		
		// 西暦からの生成
		JapaneseDate japaneseDate = JapaneseDate.of(2019, 4, 2);

		// 和暦からの生成(japaneseDateと同日)
		//JapaneseDate fromWareki = JapaneseDate.of(JapaneseEra.HEISEI, 31, 4, 1);
		JapaneseDate fromWareki = JapaneseDate.of(mitei, 31, 4, 1);
		System.out.println("1:" + fromWareki.toString());

		// 元号の取得
		Era era = japaneseDate.getEra();
		System.out.println("2:" + era.toString());

		// 元号の年の取得
		int yearOfEra = japaneseDate.get(ChronoField.YEAR_OF_ERA);
		System.out.println("3:" + yearOfEra);

		// ｢平成28年｣と表示
		DateTimeFormatter kanjiFormatter = DateTimeFormatter.ofPattern("Gyy年");
		String kanjiFormatted = kanjiFormatter.format(japaneseDate);
		System.out.println("4:" + kanjiFormatted);

		// ｢H.28｣と表示
		DateTimeFormatter alphabetFormatter = DateTimeFormatter.ofPattern("GGGGG.yy");
		String alphabetFormatted = alphabetFormatter.format(japaneseDate);
		System.out.println("5:" + alphabetFormatted);
	}

}
