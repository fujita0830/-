package com.internousdev.cyan.util;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils
;
public class InputChecker {



	/**
	 * このプログラムは渡された値を正規表現か検証するものです。
	 * 使い方はdoCheckに9つの引数を渡します。後半部分には当てはまる形式にtrueを入れます。
	 * emailを判別したいのであれば英字、数字、記号を含むため下記のようになります。
	 * 例(質問の内容,値,最小文字数,最大文字数,true,false,false,true,true)となります。
	 * 結果はList形式で渡されることに気を付けてください。
	 * @param availableHalfWidthSpace TODO
	 * @param availableFullWidthSpace TODO
	 *
	 */
	public List<String> doCheck(String propertyName,String value,int minLength,int maxLength,boolean availableAlphabeticCharacters,boolean availableKanji,boolean availableHiragana,boolean availableHalfWidthDigit,boolean availableHalfWidthSymbols,boolean availableKatakana,boolean availableFullWidthSymbols, boolean availableHalfWidthSpace, boolean availableFullWidthSpace){

		//検証した結果を入れるList
		List<String> stringList = new ArrayList<String>();
		List<String> characterTypeList = new ArrayList<String>();

		//入力欄が空かどうかを検証します
		if(StringUtils.isEmpty(value)){
			stringList.add(propertyName + "を入力してください。");
		}

		//入力欄が最小文字数以上、最大文字数以下かどうかを検証します
		if(value.length() < minLength || value.length() > maxLength){
			stringList.add(propertyName + "は" + minLength + "文字以上" + maxLength + "文字以下で入力してください。");
		}


		///////////入力された文字のタイプから何を判別するか決めます//////////
		String regularExpression = "";



		if(availableAlphabeticCharacters || availableKanji || availableHiragana || availableHalfWidthDigit || availableHalfWidthSymbols||availableKatakana||availableFullWidthSymbols || availableHalfWidthSpace||availableFullWidthSpace){
			regularExpression = "[";
		}

		if(availableAlphabeticCharacters){
			regularExpression +="a-zA-Z";
			characterTypeList.add("半角英字");
		}

		if(availableKanji){
			regularExpression +="一-龠";
			characterTypeList.add("漢字");
		}

		if(availableHiragana){
			regularExpression +="ぁ-んー";
			characterTypeList.add("ひらがな");
		}

		if(availableHalfWidthDigit){
			regularExpression +="0-9";
			characterTypeList.add("半角数字");
		}

		if(availableHalfWidthSymbols){
			regularExpression +="@.,;:!#$%&'*+-/=?^_`{|}~";
			characterTypeList.add("半角記号");
		}

		if(availableKatakana){
			regularExpression +="ァ-ヺ";
			characterTypeList.add("カタカナ");
		}

		if(availableFullWidthSymbols){
			regularExpression +="、。＠．，；：！＃＄％＆’＊＋／＝？＾＿｀｛｜｝～";
			characterTypeList.add("全角記号");
		}

		if(availableHalfWidthSpace){
			regularExpression +=" ";
			characterTypeList.add("半角スペース");
		}

		if(availableFullWidthSpace){
			regularExpression +="　";
			characterTypeList.add("全角スペース");
		}
		if(!StringUtils.isEmpty(regularExpression)){
			regularExpression +="]+";
		}
		////////////////////////////ここまで///////////////////////////


		//判別した項目に応じてエラーメッセージを返します
		String characterType = "";
		for(int i = 0;i < characterTypeList.size();i++){
			if(i == 0){
				characterType += characterTypeList.get(i).toString();
			}else{
				characterType += "、" + characterTypeList.get(i).toString();
			}
		}

		if(!value.matches(regularExpression)&&!value.equals("")){
			stringList.add(propertyName + "は" + characterType + "で入力してください。");
		}


		return stringList;
	}


	//一度目のパスワードと二度目のパスワードが同じかを検証します。
	public List<String> doPasswordCheck(String password,String reConfirmationPassword){
		List<String> stringList = new ArrayList<String>();
		if(!(password.equals(reConfirmationPassword))){
			stringList.add("新しいパスワードと(再確認)が一致しません");
		}
		return stringList;
	}
}
