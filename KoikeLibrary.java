package com.lanevok;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * KoikeLibrary．
 * <p>
 * Java実行における便利なメソッドライブラリ．<br>
 * 2014.11.18 版
 * </p>
 * @author T.Koike
 * @since 1.0
 * @version 1.1
 *
 */
public class KoikeLibrary {

	/*――――――――――――――――――――――――――――――
	 * 入力
	 * ――――――――――――――――――――――――――――――*/

	/**
	 * MyBufferedReader．
	 * <p>
	 * BufferedReaderの拡張版．
	 * </p>
	 * @author T.Koike
	 * @since 1.0
	 */
	public static class MyBufferedReader extends BufferedReader{

		/**
		 * 指定されたファイルパスでMyBufferedReaderを発行する．
		 * @since 1.0
		 * @param path ファイルパス
		 */
		public MyBufferedReader(String path){
			super(KoikeLibrary.getReader(path));
		}

		/**
		 * 一行読み込む．
		 * <p>
		 * try-catchを行います．<br>
		 * そのため例外はスローされません．
		 * </p>
		 * @since 1.0
		 * @return 読み込んだ一行のString文字列
		 */
		@Override
		public String readLine(){
			try {
				return super.readLine();
			} catch (IOException e) {
				System.err.println("読み込みでエラーが発生しました．");
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 一行読み込み，Integer型で返す．
		 * <p>
		 * try-catchを行います．<br>
		 * そのため例外はスローされません．
		 * </p>
		 * @since 1.1
		 * @return 読み込んだ一行のInteger型
		 */
		public Integer readLineInt(){
			try {
				return Integer.valueOf(super.readLine());
			} catch (IOException e) {
				System.err.println("読み込みでエラーが発生しました．");
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 一行読み込み，Double型で返す．
		 * <p>
		 * try-catchを行います．<br>
		 * そのため例外はスローされません．
		 * </p>
		 * @since 1.1
		 * @return 読み込んだ一行のDouble型
		 */
		public Double readLineDouble(){
			try {
				return Double.valueOf(super.readLine());
			} catch (IOException e) {
				System.err.println("読み込みでエラーが発生しました．");
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 全ての行を読み込み，リストで返す．
		 * @since 1.0
		 * @return 全ての行
		 */
		public List<String> readAllLine(){
			List<String> result = new ArrayList<String>();
			String line;
			while((line=readLine())!=null) result.add(line);
			return result;
		}

		/**
		 * 全ての行を読み込み，Mapで返す．
		 * @since 1.0
		 * @param delimiter 区切り文字
		 * @return Map
		 */
		public Map<String, String> readToMap(String delimiter){
			Map<String, String> map = new HashMap<String, String>();
			String line;
			while((line=readLine())!=null){
				String[] split = line.split(delimiter);
				map.put(split[0], split[1]);
			}
			return map;
		}

		/**
		 * 一行読み込み，区切り文字で区切った，String型配列で返す．
		 * @since 1.0
		 * @param delimiter 区切り文字
		 * @return String型配列
		 */
		public String[] readLineSplit(String delimiter){
			return readLine().split(delimiter);
		}

		/**
		 * クローズする．
		 * <p>
		 * try-catchを行います．<br>
		 * そのため例外はスローされません．
		 * </p>
		 * @since 1.0
		 */
		@Override
		public void close(){
			try {
				super.close();
			} catch (IOException e) {
				System.err.println("ファイルをクローズできませんでした．");
				e.printStackTrace();
			}
		}
	}

	/**
	 * MyBufferedReaderの内部Readerを作成する．
	 * <p>
	 * try-catchを行います．<br>
	 * そのため例外はスローされません．
	 * </p>
	 * @since 1.0
	 * @param path ファイルパス
	 * @return Reader
	 */
	private static Reader getReader(String path){
		try {
			return new InputStreamReader(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			System.err.println("指定されたファイルが見つかりませんでした．");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 指定されたファイルパスと文字コードでBufferedReaderを発行する．
	 * <p>
	 * try-catchを行います．<br>
	 * そのため例外はスローされません．
	 * </p>
	 * @since 1.0
	 * @param path ファイルパス
	 * @param code 文字コード
	 * @return BufferedReader
	 */
	public static BufferedReader createReader(String path, String code) {
		try {
			return new BufferedReader(new InputStreamReader(new FileInputStream(path),code));
		} catch (FileNotFoundException e) {
			System.err.println("指定されたファイルが見つかりませんでした．");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.err.println("指定された文字コードが適切ではありません．");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 指定されたファイルパスでBufferedReaderを発行する．
	 * <p>
	 * try-catchを行います．<br>
	 * そのため例外はスローされません．
	 * </p>
	 * @since 1.0
	 * @param path ファイルパス
	 * @return BufferedReader
	 */
	public static BufferedReader createReader(String path) {
		try {
			return new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		} catch (FileNotFoundException e) {
			System.err.println("指定されたファイルが見つかりませんでした．");
			e.printStackTrace();
		}
		return null;
	}

	/*――――――――――――――――――――――――――――――
	 * 出力
	 * ――――――――――――――――――――――――――――――*/

	/**
	 * MyBufferedWriter．
	 * <p>
	 * BufferedWriterの拡張版．
	 * </p>
	 * @author T.Koike
	 * @since 1.0
	 */
	public static class MyBufferedWriter extends BufferedWriter{

		/**
		 * 指定されたファイルパスでMyBufferedWriterを発行する．
		 * <p>
		 * try-catchを行います．<br>
		 * そのため例外はスローされません．
		 * </p>
		 * @param path ファイルパス
		 */
		public MyBufferedWriter(String path){
			super(KoikeLibrary.getWriter(path,false));
		}

		/**
		 * 指定されたファイルパスでMyBufferedWriterを発行する．
		 * @since 1.0
		 * @param path ファイルパス
		 * @param append 上書きフラグ
		 */
		public MyBufferedWriter(String path, boolean append){
			super(KoikeLibrary.getWriter(path, append));
		}

		/**
		 * 文字列を書き込む．
		 * <p>
		 * try-catchを行います．<br>
		 * そのため例外はスローされません．
		 * </p>
		 * @since 1.0
		 * @param str 書き込む文字列
		 */
		@Override
		public void write(String str){
			try {
				super.write(str);
			} catch (IOException e) {
				System.err.println("書き込めませんでした．");
				e.printStackTrace();
			}
		}

		/**
		 * Objectを書き込む．
		 * <p>
		 * try-catchを行います．<br>
		 * そのため例外はスローされません．
		 * </p>
		 * @since 1.1
		 * @param o 書き込むObject
		 */
		public void write(Object o){
			write(o.toString());
		}

		/**
		 * 改行文字を書込む．
		 * <p>
		 * try-catchを行います．<br>
		 * そのため例外はスローされません．
		 * </p>
		 * @since 1.0
		 */
		@Override
		public void newLine(){
			try {
				super.newLine();
			} catch (IOException e) {
				System.err.println("書き込めませんでした．");
				e.printStackTrace();
			}
		}

		/**
		 * 文字列を改行文字と共に書き込む．
		 * @since 1.0
		 * @param str 書き込む文字列
		 */
		public void writeln(String str){
			write(str);
			newLine();
		}

		/**
		 * Objectを改行文字と共に書き込む．
		 * @since 1.1
		 * @param o 書き込むObject
		 */
		public void writeln(Object o){
			write(o.toString());
			newLine();
		}

		/**
		 * 文字列を書込み，その後フラッシュする．
		 * @since 1.0
		 * @param str 書き込む文字列
		 */
		public void writeFlush(String str){
			write(str);
			flush();
		}

		/**
		 * Objectを書込み，その後フラッシュする．
		 * @since 1.1
		 * @param o 書き込むObject
		 */
		public void writeFlush(Object o){
			write(o.toString());
			flush();
		}

		/**
		 * 文字列を改行文字と共に書込み，その後フラッシュする．
		 * @since 1.0
		 * @param str 書き込む文字
		 */
		public void writelnFlush(String str){
			write(str);
			newLine();
			flush();
		}

		/**
		 * Objectを改行文字と共に書込み，その後フラッシュする．
		 * @since 1.1
		 * @param o 書き込むObject
		 */
		public void writelnFlush(Object o){
			write(o.toString());
			newLine();
			flush();
		}

		/**
		 * フラッシュする．
		 * <p>
		 * バッファの内容が明示的に書込み指示できます．<br>
		 * try-catchを行います．<br>
		 * そのため例外はスローされません．
		 * </p>
		 * @since 1.0
		 */
		@Override
		public void flush(){
			try {
				super.flush();
			} catch (IOException e) {
				System.err.println("フラッシュできませんでした．");
				e.printStackTrace();
			}
		}

		/**
		 * クローズします．
		 * <p>
		 * try-catchを行います．<br>
		 * そのため例外はスローされません．
		 * </p>
		 * @since 1.0
		 */
		@Override
		public void close(){
			try {
				super.close();
			} catch (IOException e) {
				System.err.println("ファイルをクローズできませんでした．");
				e.printStackTrace();
			}
		}
	}

	/**
	 * MyBufferedWriterの内部Writerを作成する．
	 * <p>
	 * try-catchを行います．<br>
	 * そのため例外はスローされません．
	 * </p>
	 * @since 1.0
	 * @param path ファイルパス
	 * @return Writer
	 */
	private static Writer getWriter(String path, boolean append){
		try {
			return new OutputStreamWriter(new FileOutputStream(path,append));
		} catch (FileNotFoundException e) {
			System.err.println("指定されたパスへ書込み準備ができませんでした．");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * System.out の出力先をファイルパスにする．
	 * <p>
	 * try-catchを行います．<br>
	 * そのため例外はスローされません．
	 * </p>
	 * @since 1.0
	 * @param path ファイルパス
	 */
	public static void setSystemOut(String path){
		try {
			System.setOut(new PrintStream(new File(path)));
		} catch (FileNotFoundException e) {
			System.err.println("指定されたパスにファイルが作成できません．");
			e.printStackTrace();
		}
	}

	/**
	 * System.err の出力先をファイルパスにする．
	 * <p>
	 * try-catchを行います．<br>
	 * そのため例外はスローされません．
	 * </p>
	 * @since 1.0
	 * @param path ファイルパス
	 */
	public static void setSystemErr(String path){
		try {
			System.setErr(new PrintStream(new File(path)));
		} catch (FileNotFoundException e) {
			System.err.println("指定されたパスにファイルが作成できません．");
			e.printStackTrace();
		}
	}

	/**
	 * 指定されたファイル名でBufferedWriterを発行する
	 * @since 1.0
	 * @param path パス名
	 * @return BufferedWriter
	 * @throws FileNotFoundException
	 */
	public static BufferedWriter createWriter(String path) {
		return createWriter(path, false);
	}

	/**
	 * 指定されたファイル名でBufferedWriterを発行する
	 * @since 1.0
	 * @param path パス名
	 * @param append 上書き許可 (=true)
	 * @return BufferedWriter
	 */
	public static BufferedWriter createWriter(String path, boolean append) {
		try {
			return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,append)));
		} catch (FileNotFoundException e) {
			System.err.println("指定されたパスにファイルが作成できません．");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 多次元配列までの内容を出力します．
	 * @param obj 対象の配列
	 * @since 1.0
	 */
	public static void debugArrays(Object... obj) {
		System.out.println(Arrays.deepToString(obj));
	}

	/**
	 * 2次元配列マップを出力します．
	 * <p>
	 * 下方向にi，横方向にjとして出力されます．
	 * </p>
	 * @param map 出力対象2次元配列
	 */
	public static void printMaping(int[][] map) {
		System.out.println("-------- map display ---------");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.printf("%3d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println("----------------------------");
		System.out.println();
	}

	/*――――――――――――――――――――――――――――――
	 * 統計
	 * ――――――――――――――――――――――――――――――*/


/**
     * 箱ひげ図で必要な値を取得する．
     * <p>
     * 1.1で引数がList<Integer>からList<Double>に変更されました．
     * </p>
     * @since 1.1
     * @param value 対象データリスト
     * @return {最小値,第一四分位数,中央値,第三四分位数,最大値}
     */
     public static List<Double> getBoxPlotValue(List<Double> value){
          Collections.sort(value);
          double center, first, third, start = 0, goal = value.size()-1;
          double down = Math.floor((start+goal)*1.0/2);
          double up = Math.ceil((start+goal)*1.0/2);
          center = (value.get((int)down)+value.get((int)up))*1.0/2;
          double down1 = Math.floor((start+down)*1.0/2);
          double up1 = Math.ceil((start+down)*1.0/2);
          first = (value.get((int)down1)+value.get((int)up1))*1.0/2;
          double down2 = Math.floor((up+goal)*1.0/2);
          double up2 =  Math.ceil((up+goal)*1.0/2);
          third = (value.get((int)down2)+value.get((int)up2))*1.0/2;
          Double min = Double.valueOf(value.get(0));
          Double max = Double.valueOf(value.get(value.size()-1));
          List<Double> result = new ArrayList<Double>();
          result.add(min);
          result.add(first);
          result.add(center);
          result.add(third);
          result.add(max);
          return result;
     }

	/**
	 * PrecisionとRecallからF値を算出する
	 * @since 1.0
	 * @param precision
	 * @param recall
	 * @return F-Measure
	 */
	public static double getFmeasure(double precision, double recall){
		return (2.0*precision*recall)/(precision+recall);
	}

	/**
	 * mapの平均値，最大値，最小値を出力する．
	 * @since 1.0
	 * @param map 対象のmap
	 */
	public static void printAveMaxMin(Map<Integer, Integer> map) {
		int sum = 0;
		int count = 0;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(Integer key : map.keySet()){
			Integer value = map.get(key);
			sum += key*value;
			count += value;
			max = Math.max(max, key);
			min = Math.min(min, key);
		}
		KoikeLibrary.printAveMaxMin(sum*1.0/count, max, min);
	}

	/**
	 * listの平均値，最大値，最小値を出力する．
	 * @since 1.0
	 * @param list 対象のlist
	 */
	public static void printAveMaxMin(List<Integer> list) {
		int sum = 0;
		int size = list.size();
		for(int i=0;i<size;i++){
			int value = list.get(i);
			sum += value;
		}
		KoikeLibrary.printAveMaxMin((sum*1.0)/size, Collections.max(list), Collections.min(list));
	}

	/**
	 * 平均値，最大値，最小値の説明書きを付加した状態で出力する．
	 * @since 1.0
	 * @param ave 平均値
	 * @param max 最大値
	 * @param min 最小値
	 */
	private static void printAveMaxMin(double ave, int max, int min){
		System.out.println("Average\t : "+ave);
		System.out.println("Max\t : "+max);
		System.out.println("Min\t : "+min);
	}

	/**
	 * リスト内要素の平均値を出力する．
	 * @since 1.0
	 * @param list 対象のリスト
	 */
	public static void printAverage(List<Integer> list){
		int sum = 0;
		int size = list.size();
		for(int i=0;i<size;i++) sum+=list.get(i);
		System.out.println("Average : "+(sum*1.0/size));
	}

	/**
	 * リスト内要素について，何がいくつ格納されているかで返す．
	 * @since 1.0
	 * @param list 対象のlist
	 * @return map<要素名,要素名に対する個数>
	 */
	public static Map<Integer, Integer> countElement(List<Integer> list){
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for(Integer key : list){
			if(map.containsKey(key)){
				int before = map.get(key);
				map.put(key, ++before);
			}
			else map.put(key, 1);
		}
		return map;
	}

	/**
	 * リスト内要素について，何がいくつ，全体に対してどれくらいの割合で格納されているか出力する．
	 * <p>
	 * e.g. list {1,1,1,2,2,3} であれば，<br>
	 * map {value1=3elements (0.5%), value2=2elements (0.33%), value3=1elements (0.17%)}<br>
	 * が出力される．
	 * </p>
	 * @since 1.0
	 * @param list 対象のlist
	 */
	public static void printRate(List<Integer> list){
		Map<Integer, Integer> map = countElement(list);
		int size = list.size();
		for(Integer key : map.keySet()){
			double rto = (map.get(key)*100/(double)size);
			System.out.printf("key : < %d >\t...(%4d) [%.2f%s]\n",key,map.get(key),rto,"%");
		}
	}

	/**
	 * リスト内要素について，何が何個格納されているのを何種類あるか と全体に対しての割合を出力する．
	 * <p>
	 * e.g. list {1,1,1,2,2,3,3,4,4,5,6} であれば，<br>
	 * value種類数(species)は，6である．
	 * map {1elements=2species (0.33%), 2elements=3species (0.5%), 3elements=1species (0.17%)}<br>
	 * が出力される．
	 * </p>
	 * @since 1.0
	 * @param list 対象のリスト
	 */
	public static void printCountRate(List<Integer> list){
		Map<Integer, Integer> map = countValue(countElement(list));
		int size = list.size();
		for(Integer value : map.keySet()){
			double rto = (map.get(value)*100/(double)size);

			System.out.printf("value : < %d >\t...(%4d) [%.2f%s]\n",value,map.get(value),rto,"%");
		}
	}

	/**
	 * mapのvalueについて，何がいくつ格納されているかを返す．
	 * @since 1.0
	 * @param map 対象となるmap
	 * @return map<valueに格納されている要素名,要素名に対する個数>
	 */
	public static Map<Integer, Integer> countValue(Map<Integer, Integer> map){
		Map<Integer, Integer> ans = new TreeMap<Integer, Integer>();
		for(Integer key : map.keySet()){
			Integer value = map.get(key);
			if(ans.containsKey(value)){
				Integer before = ans.get(value);
				ans.put(value, ++before);
			}
			else ans.put(value, 1);
		}
		return ans;
	}

	/**
	 * mapのvalue総和を求める．
	 * @since 1.0
	 * @param map 対象となるmap
	 * @return value総和
	 */
	public static Integer getValueSum(Map<Integer, Integer> map){
		int sum = 0;
		for(Integer key : map.keySet()){
			sum += map.get(key);
		}
		return sum;
	}


	/*――――――――――――――――――――――――――――――
	 * 演算
	 * ――――――――――――――――――――――――――――――*/

	/**
	 * 積和演算(乗累算)を行う．
	 * <p>
	 * Multiply and ACcumulation．<br>
	 * つまり、sigma(map.keyset) key*value
	 * <p>
	 * @since 1.0
	 * @param map 積和演算計算対象Map
	 * @return 積和演算結果
	 */
	public static Integer getMAC(Map<Integer, Integer> map){
		int sum = 0;
		for(Integer key : map.keySet()){
			Integer value = map.get(key);
			sum += key*value;
		}
		return sum;
	}

	/**
	 * 指定されたサイズをn分割した際の、分割点インデックスを得る．
	 * @since 1.0
	 * @param n 分割数
	 * @param size 分割前要素数
	 * @return Map<part,start>
	 */
	public static Map<Integer, Integer> getStartPosDivideN(int n, int size){
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		int index = 0;
		int slide = size/n;
		for(int i=0;i<n;i++){
			map.put(i, index);
			index+=slide;
		}
		return map;
	}

	/**
	 * サロゲートペア(顔文字)を考慮した文字列長を取得する．
	 * @since 1.0
	 * @param str 文字列
	 * @return 文字長
	 * @deprecated getSuperLengthでUnicodeの濁点も考慮されます．
	 */
	public static int getCodePointCount(String str) {
		return str.codePointCount(0, str.length());
	}

	/**
	 * サロゲートペア(顔文字など)とUnicodeの濁点を考慮した文字長を取得する．
	 * @since 1.0
	 * @param str 文字列
	 * @return 文字長
	 */
	public static int getSuperLength(String str) {
		String after = Normalizer.normalize(str, Normalizer.Form.NFC);
		return after.codePointCount(0, after.length());
	}

	/*――――――――――――――――――――――――――――――
	 * システムまたファイル操作
	 * ――――――――――――――――――――――――――――――*/

	/**
	 * 強制的にガベージコレクションを実行する．
	 * @since 1.0
	 */
	public static void doGarbageCollection(){
		System.gc();
	}

	/**
	 * 指定した秒数だけ実行を一時停止．
	 * <p>
	 * try-catchを行います．<br>
	 * そのため例外はスローされません．
	 * </p>
	 * @since 1.0
	 * @param seconds 一時停止させる秒数
	 */
	public static void sleepSec(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	/**
	 * システムプロパティ情報を出力する．
	 * @since 1.0
	 */
	public static void printProperties(){
		Properties props = System.getProperties();
		props.list(System.err);
	}

	/**
	 * Java 仮想マシンのメモリ情報を返す．
	 * @since 1.0
	 */
	public static void printMemoryInfo() {
		DecimalFormat f1 = new DecimalFormat("#,###KB");
		DecimalFormat f2 = new DecimalFormat("##.#");
		long free = Runtime.getRuntime().freeMemory()/1024;
		long total = Runtime.getRuntime().totalMemory()/1024;
		long max = Runtime.getRuntime().maxMemory()/1024;
		long used = total - free;
		double ratio = (used * 100 / (double) total );
		String info = "Total = " + f1.format(total) + " , " +
				"Used = " + f1.format(used) + " (" + f2.format(ratio) + "%) , " +
				"Free = "+f1.format(max);
		System.err.println(info);
	}

	/**
	 * ストップウォッチ
	 * <p>
	 * 実行時間を計測することができます．
	 * </p>
	 * @author T.Koike
	 * @since 1.0
	 */
	public static class StopWatch {
		private long SplitTime;
		private long LapTime;

		/**
		 * ストップウォッチをスタートします．
		 * <p>
		 * コンストラクタを呼び出すことで，リスタートすることが可能です．
		 * </p>
		 * @since 1.0
		 */
		public StopWatch(){
			SplitTime = System.currentTimeMillis();
			LapTime = System.currentTimeMillis();
		}

		/**
		 * スプリットタイムを取得します．
		 * @since 1.0
		 * @return スプリットタイム
		 */
		public long getSplitTime(){
			return System.currentTimeMillis() - SplitTime;
		}

		/**
		 * ラップタイムを取得します．
		 * <p>
		 * 次ラップのスタート時間として設定もされます．
		 * </p>
		 * @since 1.0
		 * @return ラップタイム
		 */
		public long getLapTime(){
			long time = System.currentTimeMillis() - LapTime;
			LapTime = System.currentTimeMillis();
			return time;
		}

		/**
		 * スプリットタイムを文字列で取得します．
		 * @since 1.0
		 * @return スプリットタイム
		 */
		public String getSplitTimeString(){
			return TimeToString(getSplitTime());
		}

		/**
		 * ラップタイムを文字列で取得します．
		 * <p>
		 * 次ラップのスタート時間として設定もされます．
		 * </p>
		 * @since 1.0
		 * @return ラップタイム
		 */
		public String getLapTimeString(){
			return TimeToString(getLapTime());
		}

		/**
		 * ミリ秒long時間を分かり易く文字列に変換する．
		 * @since 1.0
		 * @param time ミリ秒時間
		 * @return day,hour,minute,second,msec での文字列
		 */
		private String TimeToString(long time){
			String timeStr;

			//ミリ秒
			timeStr = String.valueOf(time % 1000) + "ms";
			//秒
			if(time >= 1000){
				time /= 1000;
				timeStr = String.valueOf(time % 60) + "s "+ timeStr;
			}else{
				return timeStr;
			}
			//分
			if(time >= 60){
				time /= 60;
				timeStr = String.valueOf(time % 60) + "m " + timeStr;
			}else{
				return timeStr;
			}
			//時間
			if(time >= 60){
				time /= 60;
				timeStr = String.valueOf(time % 60) + "h " + timeStr;
			}
			else{
				return timeStr;
			}
			//日
			if(time >= 24){
				time /= 24;
				timeStr = String.valueOf(time) + "d " + timeStr;
			}
			return timeStr;
		}
	}

	/*――――――――――――――――――――――――――――――
	 * 高速処理系
	 * ――――――――――――――――――――――――――――――*/

	/**
	 * 高速splitをする．．
	 * <p>
	 * 内部実装は，StringTokenizerです．
	 * </p>
	 * @since 1.0
	 * @param str 対象の文字列
	 * @param delimiter 区切り文字
	 * @return split結果のString配列
	 */
	public static String[] split(String str, String delimiter) {
		StringTokenizer tokenizer = new StringTokenizer(str, delimiter);
		String[] resultStr = new String[tokenizer.countTokens()];
		int i = 0;
		while (tokenizer.hasMoreTokens()) {
			resultStr[i++] = tokenizer.nextToken();
		}
		return resultStr;
	}

	/**
	 * 高速標準入力．
	 * <p>
	 * 以下のように使用します．<br>
	 * <code>
	 * public class Main{
	 * 	FastScanner in = new FastScanner(System.in);
	 * 	PrintWriter out = new PrintWriter(System.out);
	 *
	 * 	public void run() {
	 * 		System.out.println(in.nextInt() * in.nextInt());
	 * 		out.close();
	 * 	}
	 *
	 * 	public static void main(String[] args) {
	 * 		new Main().run();
	 * 	}
	 * }
	 * </code>
	 * </p>
	 * @since 1.0
	 * @deprecated 競技プログラミング用です．
	 */
	public static class FastScanner {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;

		public FastScanner(InputStream stream) {
			this.stream = stream;
		}

		int read() {
			if (numChars == -1)
				throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		boolean isEndline(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		int[] nextIntArray(int n) {
			int[] array = new int[n];
			for (int i = 0; i < n; i++)
				array[i] = nextInt();
			return array;
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		long[] nextLongArray(int n) {
			long[] array = new long[n];
			for (int i = 0; i < n; i++)
				array[i] = nextLong();
			return array;
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		double[] nextDoubleArray(int n) {
			double[] array = new double[n];
			for (int i = 0; i < n; i++)
				array[i] = nextDouble();
			return array;
		}

		String next() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		String[] nextStringArray(int n) {
			String[] array = new String[n];
			for (int i = 0; i < n; i++)
				array[i] = next();
			return array;
		}

		String nextLine() {
			int c = read();
			while (isEndline(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isEndline(c));
			return res.toString();
		}
	}

	/**
	 * 配列の高速コピー．
	 * @since 1.0
	 * @param src コピー元の配列
	 */
	public static int[] copyArray(int[] src){
		int[] dst = new int[src.length];
		System.arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	/**
	 * 安全な乱数ジェネレータを得る．
	 * <p>
	 * シードの生成に，Math.random()，現在時刻ミリ秒，空メモリ容量を使用．
	 * </p>
	 * @since 1.0
	 * @return Randomクラス
	 */
	public static Random getSafetyRandomGenerator(){
		long seed1 = Double.valueOf(Math.random()*1000000000000000L).longValue();
		long seed2 = System.currentTimeMillis();
		long seed3 = Runtime.getRuntime().freeMemory();
		long seed = seed1+seed2+seed3;
		return new Random(seed);
	}

	/**
	 * 強制的にプログラムを終了します．
	 * <p>
	 * 0をシステムへ返します．
	 * </p>
	 * @since 1.0
	 */
	public static void doForceSystemExit(){
		System.exit(0);
	}

	/**
	 * 二つのファイルが同一か判定します．
	 * @since 1.0
	 * @param path1 ファイル1のパス
	 * @param path2 ファイル2のパス
	 * @return 同一であるかどうか
	 */
	public static boolean isEqualsFile(String path1, String path2){
		MyBufferedReader br1 = new MyBufferedReader(path1);
		List<String> file1 = br1.readAllLine();
		MyBufferedReader br2 = new MyBufferedReader(path2);
		List<String> file2 = br2.readAllLine();
		if(file1.size()!=file2.size()) {
			br1.close();
			br2.close();
			return false;
		}
		int index = 0;
		for(String line1 : file1){
			if(!line1.equals(file2.get(index++))){
				br1.close();
				br2.close();
				return false;
			}
		}
		br1.close();
		br2.close();
		return true;
	}

	/**
	 * 二つのファイルが差分を出力します．
	 * @since 1.0
	 * @param path1 ファイル1のパス
	 * @param path2 ファイル2のパス
	 */
	public static void printDiffFile(String path1, String path2){
		MyBufferedReader br1 = new MyBufferedReader(path1);
		List<String> file1 = br1.readAllLine();
		MyBufferedReader br2 = new MyBufferedReader(path2);
		List<String> file2 = br2.readAllLine();
		int size = file1.size()>file2.size()?file1.size():file2.size();
		int min = file1.size()<file2.size()?file1.size():file2.size();
		for(int i=0;i<size;i++){
			if(i>=min){
				System.err.println("一方のファイルが終端になりました．");
				break;
			}
			if(!file1.get(i).equals(file2.get(i))){
				System.out.println(i+"\t : "+file1.get(i)+"<==>"+file2.get(i));
			}
		}
		br1.close();
		br2.close();
		return;
	}

	/*――――――――――――――――――――――――――――――
	 * 変換また取得系
	 * ――――――――――――――――――――――――――――――*/

	/**
	 * Integer集合からIntegerリストに変換する．
	 * @since 1.0
	 * @param set 集合
	 * @return リスト
	 */
	public static List<Integer> getListFromSet(Set<Integer> set){
		List<Integer> res = new ArrayList<Integer>();
		for(Integer key : set) res.add(key);
		return res;
	}

	/**
	 * mapのvalueをlistで取得する．
	 * @since 1.0
	 * @param map 対象となるmap
	 * @return map全てのvalueが入ったlist
	 */
	public static List<Integer> getValueList(Map<Integer, Integer> map){
		List<Integer> list = new ArrayList<Integer>();
		for(Integer key : map.keySet()){
			list.add(map.get(key));
		}
		return list;
	}

	/**
	 * 配列をString型リストに変換し取得する．
	 * @since 1.0
	 * @param array 配列
	 * @return String型リスト
	 */
	public static List<String> getListFromArrayOnString(String[] array){
		List<String> list = new ArrayList<String>();
		for(String str : array){
			list.add(str);
		}
		return list;
	}

	/**
	 * String型リストからInteger型リストに変換して取得する．
	 * @since 1.0
	 * @param list String型リスト
	 * @return Integer型リスト
	 */
	public static List<Integer> getListIntegerFromString(List<String> list) {
		List<Integer> result = new ArrayList<Integer>();
		for(String str : list){
			result.add(Integer.parseInt(str));
		}
		return result;
	}

	/**
	 * String型リストの要素をデリミタで分割し，インデックス番目をIntegerでリストにまとめて取得する．
	 * @since 1.0
	 * @param list 対象のString型リスト
	 * @param delimiter デリミタ
	 * @param index 取得番地
	 * @return Integer型リスト
	 */
	public static List<Integer> getListIntegerFromString(List<String> list, String delimiter, int index) {
		List<Integer> result = new ArrayList<Integer>();
		for(String str : list){
			String[] split = str.split(delimiter);
			result.add(Integer.parseInt(split[index]));
		}
		return result;
	}

	/**
	 * String型リストの要素をデリミタで分割し，インデックス番目をDoubleでリストにまとめて取得する．
	 * @since 1.1
	 * @param list 対象のString型リスト
	 * @param delimiter デリミタ
	 * @param index 取得番地
	 * @return Double型リスト
	 */
	public static List<Double> getListDoubleFromString(List<String> list, String delimiter, int index) {
		List<Double> result = new ArrayList<Double>();
		for(String str : list){
			String[] split = str.split(delimiter);
			result.add(Double.parseDouble(split[index]));
		}
		return result;
	}

	/**
	 * 文字列を区切り文字で分割し，指定されたインデックスの文字列を取得します．
	 * @since 1.0
	 * @param str 対象となる文字列
	 * @param delimiter デリミタ
	 * @param index 取得するインデックス
	 * @return 取得した文字列
	 */
	public static String getStringSplit(String str, String delimiter, int index){
		String[] split = str.split(delimiter);
		return split[index];
	}

	/**
	 * Integer型リストをDouble型リストに変換し取得します．
	 * @since 1.1
	 * @param list Integer型リスト
	 * @return Double型リスト
	 */
	public static List<Double> getConvertDoubleList(List<Integer> list){
		List<Double> result = new ArrayList<Double>();
		for(Integer v : list){
			result.add(Double.valueOf(v));
		}
		return result;
	}

	/**
	 * Double型リストをInteger型リストに変換し取得します．
	 * @since 1.1
	 * @param list Double型リスト
	 * @return Integer型リスト
	 */
	public static List<Integer> getConvertIntegerList(List<Double> list){
		List<Integer> result = new ArrayList<Integer>();
		for(Double v : list){
			result.add(Integer.valueOf(String.valueOf(v)));
		}
		return result;
	}

	/**
	 * String型リストをコピーし，取得する．
	 * @since 1.1
	 * @param list コピー元String型リスト
	 * @return コピーされたString型リスト
	 */
	public static List<String> copy(List<String> list){
		return new ArrayList<String>(list);
	}

	/*――――――――――――――――――――――――――――――
	 * ソート
	 * ――――――――――――――――――――――――――――――*/

	/**
	 * MapをValueでソートします．
	 * <p>
	 * ValueがIntegerもしくはDoubleの場合，値の降順となり，<br>
	 * ValueがStringの場合，辞書式順になります．<br>
	 * 受け取り時は必要に応じて，キャストしてください．<br>
	 * 逆順希望の場合は，第2引数reverseでtrueにしてください。
	 * </p>
	 * @since 1.0
	 * @param map ソート前のマップ
	 * @return ソート後のマップ
	 */
	public static Map<?, ?> getMapValueSort(Map<?, ?> map) {
		return getMapValueSort(map, false);
	}

	/**
	 * MapをValueでソートします．
	 * <p>
	 * ValueがIntegerもしくはDoubleの場合，値の降順となり，<br>
	 * ValueがStringの場合，辞書式順になります．<br>
	 * 受け取り時は必要に応じて，キャストしてください．<br>
	 * 逆順希望の場合は，第2引数reverseでtrueにしてください。
	 * </p>
	 * @since 1.0
	 * @param map ソート前のマップ
	 * @param reverse 逆順ソート
	 * @return ソート後のマップ
	 */
	public static Map<?, ?> getMapValueSort(Map<?, ?> map, boolean reverse) {
		List<Map.Entry<?, ?>> entries = new LinkedList<Map.Entry<?, ?>>(map.entrySet());
		Object ob = null;
		try {
			ob = entries.get(0).getValue();
		} catch (IndexOutOfBoundsException e) {
			System.err.println("要素がありませんでした．");
			e.printStackTrace();
			return map;
		}
		if(ob instanceof Double == true) Collections.sort(entries, DoubleComparator(reverse));
		else if(ob instanceof Integer == true) Collections.sort(entries, IntegerComparator(reverse));
		else if(ob instanceof String == true) Collections.sort(entries, StringComparator(reverse));
		else{
			System.err.println("ソート可能な型ではありませんでした．");
			return map;
		}
		Map<Object, Object> ranking = new LinkedHashMap<Object, Object>();
		for (Map.Entry<?, ?> entry : entries) 	ranking.put(entry.getKey(), entry.getValue());
		return ranking;
	}

	/**
	 * String用Comparatorを作成する．
	 * <p>
	 * 順序は，辞書式順序です．
	 * </p>
	 * @since 1.0
	 * @param reverse 逆順フラグ
	 * @return String用Comparator
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Comparator<? super Entry<?, ?>> StringComparator(final boolean reverse) {
		return new Comparator(){
			public int compare(Object o1, Object o2){
				Map.Entry e1 =(Map.Entry)o1;
				Map.Entry e2 =(Map.Entry)o2;
				try {
					if(reverse) return ((String)e2.getValue()).compareTo((String)e1.getValue());
					return ((String)e1.getValue()).compareTo((String)e2.getValue());
				} catch (Exception e) {
					System.err.println("ソートできませんでした．型が混在している可能性があります．");
					e.printStackTrace();
				}
				return 0;
			}
		};
	}

	/**
	 * Integer用Comparatorを作成する．
	 * <p>
	 * 順序は，降順です．
	 * </p>
	 * @since 1.0
	 * @param reverse 逆順フラグ
	 * @return Integer用Comparator
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Comparator<? super Entry<?, ?>> IntegerComparator(final boolean reverse) {
		return new Comparator(){
			public int compare(Object o1, Object o2){
				Map.Entry e1 =(Map.Entry)o1;
				Map.Entry e2 =(Map.Entry)o2;
				try {
					if(reverse) return ((Integer)e1.getValue()).compareTo((Integer)e2.getValue());
					return ((Integer)e2.getValue()).compareTo((Integer)e1.getValue());
				} catch (Exception e) {
					System.err.println("ソートできませんでした．型が混在している可能性があります．");
					e.printStackTrace();
				}
				return 0;
			}
		};
	}

	/**
	 * Double用Comparatorを作成する．
	 * <p>
	 * 順序は，降順です．
	 * </p>
	 * @since 1.0
	 * @param reverse 逆順フラグ
	 * @return Double用Comparator
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Comparator<? super Entry<?, ?>> DoubleComparator(final boolean reverse) {
		return new Comparator(){
			public int compare(Object o1, Object o2){
				Map.Entry e1 =(Map.Entry)o1;
				Map.Entry e2 =(Map.Entry)o2;
				try {
					if(reverse) return ((Double)e1.getValue()).compareTo((Double)e2.getValue());
					return ((Double)e2.getValue()).compareTo((Double)e1.getValue());
				} catch (ClassCastException e) {
					System.err.println("ソートできませんでした．型が混在している可能性があります．");
					e.printStackTrace();
				}
				return 0;
			}
		};
	}

}
