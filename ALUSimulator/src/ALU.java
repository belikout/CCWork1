/**
 * 模拟ALU进行整数和浮点数的四则运算
 * @author [151250090_李紫欣]
 *
 */

public class ALU {

	/**
	 * 生成十进制整数的二进制补码表示。<br/>
	 * 例：integerRepresentation("9", 8)
	 * @param number 十进制整数。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param length 二进制补码表示的长度
	 * @return number的二进制补码表示，长度为length
	 */
	public String integerRepresentation (String number, int length) {
		// TODO YOUR CODE HERE.
		String nu=null;
		String num=null;
		if(number.charAt(0)!='-'){
			double k=Double.parseDouble(number);
			nu=Integer.toBinaryString((int)k);
			for(int i=0;i<length-nu.length();i++){
				num+='0';
			}
			num+=nu;
			num=num.substring(4, num.length());
		}
		else{
			int m=0;
			String str=null;
			double k=Double.parseDouble(number.substring(1));
			nu=Integer.toBinaryString((int)k);
			for(int i=0;i<length-nu.length();i++){
				num+='0';
			}
			num+=nu;
			num=num.substring(4, num.length());
			for(int i=num.length()-1;i>=0;i--){
				
				if(m==0){
					str+=num.charAt(i);
                if(num.charAt(i)=='1'){
                	m=1;
                }
				}
				else{
					if(num.charAt(i)=='1'){
						str+='0';
					}else{
						str+='1';
					}
				}
			}
			StringBuffer s=new StringBuffer(str.substring(4, str.length()));
			s=s.reverse();
			num=s.toString();
		}
		return num;
	}
	
	/**
	 * 生成十进制浮点数的二进制表示。
	 * 需要考虑 0、反规格化、正负无穷（“+Inf”和“-Inf”）、 NaN等因素，具体借鉴 IEEE 754。
	 * 舍入策略为向0舍入。<br/>
	 * 例：floatRepresentation("11.375", 8, 11)
	 * @param number 十进制浮点数，包含小数点。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @return number的二进制表示，长度为 1+eLength+sLength。从左向右，依次为符号、指数（移码表示）、尾数（首位隐藏）
	 */
	public String floatRepresentation (String number, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		String num=null;
		String two=null;
	   
	    String e;
	    String s;
	    int xone=0;
	    if(number.charAt(0)=='-'){
	    	num+='1';
	    	number=number.substring(1);
	    }
	    else{
	    	num+='0';
	    }
		String []sp=number.split("\\.");
		String one=Integer.toBinaryString(Integer.parseInt(sp[0]));
		Double two0=Double.parseDouble(number)-Double.parseDouble(sp[0]);
		int p=-1;
		if(two0==0){
			two="00000";
		}
		while(two0!=0){
			if(two0>=Math.pow(2, p)){
				two0-=Math.pow(2, p);
				two+='1';
			}else{
				two+='0';
			}
			p--;
		}
		
		two=two.substring(4, two.length());
		System.out.println(two);
		for(int i=0;i<two.length();i++){
			if(two.charAt(i)=='1'){
				xone=i+1;
				break;
			}
		}
		if(one.equals("0")){
			if(xone>=Math.pow(2, eLength)){
				e=integerRepresentation("0", eLength);
				s=two.substring((int)Math.pow(2, eLength-1)-1);
			}
			else{
				e=integerRepresentation(Double.toString(Math.pow(2,eLength-1)-1-xone), eLength);
				s=two.substring(xone);
			}
		}
		else{
			if(one.length()>=Math.pow(2, eLength)){
				e=integerRepresentation("-1", eLength);
				s=integerRepresentation("0", sLength);
			}
			else{
				e=integerRepresentation(Double.toString(Math.pow(2,eLength-1)-1+one.length()-1), eLength);
				s=one.substring(1)+two;
			}
		}
		num+=e;
		System.out.println(s);
		if(s.length()==sLength){
		}
		else  if(s.length()>sLength){
			s=s.substring(0, sLength);
		}
		else{
			int m=s.length();
			for(int i=0;i<sLength-m;i++){
				s+='0';
			}
		}
		System.out.println(s);
		num+=s;
		num=num.substring(4, num.length());
		return num;
		
	}
	
	/**
	 * 生成十进制浮点数的IEEE 754表示，要求调用{@link #floatRepresentation(String, int, int) floatRepresentation}实现。<br/>
	 * 例：ieee754("11.375", 32)
	 * @param number 十进制浮点数，包含小数点。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param length 二进制表示的长度，为32或64
	 * @return number的IEEE 754表示，长度为length。从左向右，依次为符号、指数（移码表示）、尾数（首位隐藏）
	 */
	public String ieee754 (String number, int length) {
		// TODO YOUR CODE HERE.
		if(length==32){
			return floatRepresentation(number,8,23);
		}
		else{
			return floatRepresentation(number,11,52);
		}
	}
	
	/**
	 * 计算二进制补码表示的整数的真值。<br/>
	 * 例：integerTrueValue("00001001")
	 * @param operand 二进制补码表示的操作数
	 * @return operand的真值。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 */
	public String integerTrueValue (String operand) {
		// TODO YOUR CODE HERE.
		String num=null;
		int k=0;
		if(operand.charAt(0)=='1'){
			k-=(int)Math.pow(2, operand.length()-1);
		}
		else{}
		for(int i=1;i<operand.length();i++){
			if(operand.charAt(i)=='1'){
				k+=(int)Math.pow(2, operand.length()-i-1);
			}
		}
		num+=Integer.toString(k);
		num=num.substring(4, num.length());
		return num;
	
	}
	
	/**
	 * 计算二进制原码表示的浮点数的真值。<br/>
	 * 例：floatTrueValue("01000001001101100000", 8, 11)
	 * @param operand 二进制表示的操作数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @return operand的真值。若为负数；则第一位为“-”；若为正数或 0，则无符号位。正负无穷分别表示为“+Inf”和“-Inf”， NaN表示为“NaN”
	 */
	public String floatTrueValue (String operand, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		
			String result="";
			//判断正负
			if(operand.charAt(0)=='1')
				result+="-";
			//判断指数是否全部为1或者全为0
			boolean allZero=true;
			boolean allOne=true;
			for(int i=0;i<eLength;i++){
				if(operand.charAt(i+1)=='0'){
					allOne=false;
					break;
				}

			}
			for(int i=0;i<eLength;i++){
				if(operand.charAt(i+1)=='1'){
					allZero=false;
					break;
				}
			}
		
			//计算指数的最大偏移量
					int eSum=1;
					for(int i=0;i<eLength-1;i++)
						eSum=eSum*2;
					eSum-=1;

				
		//如果是规格化数
			if(allZero==false&&allOne==false){
			double x=1;
			double x2=0;
			//计算1.f的十进制值
			for(int i=0;i<sLength;i++){
				x2=operand.charAt(i+eLength+1)-'0';
				for(int j=0;j<i+1;j++){
					x2=x2*0.5;
					
				}
				x+=x2;
			
			}
			
			
			
			int e=0;
			for(int i=0;i<eLength;i++){
				if(operand.charAt(i+1)=='1')
					e=e*2+1;
				else
					e=e*2;
			}
			
		
			for(int i=0;i<Math.abs(eSum-e);i++){
				
			//指数小于011111，除以2
			if(e<eSum)
				x=x/2;
			//指数大于011111，乘以2
			if(e>eSum)
				x=x*2;							
				}
			
			result+=x+"";
			return result;			
			}
			//结果为0或非规格化数
			else if(allZero==true){
				boolean sZero=true;
				for(int i=0;i<eLength;i++){
					if(operand.charAt(i+eLength+1)=='1'){
						sZero=false;
						break;
					}
				}
				//结果为0
				if(sZero==true){
					return "0";
				
				}
		      //非规格化
				else{
					
				double x=0;
				double x2=0;
				//计算0.f的十进制值
				for(int i=0;i<sLength+1;i++){
					x2=operand.charAt(i)-'0';
					for(int j=0;j<i+1;j++){
						x2=x2*0.5;
					}
					x+=x2;
				}
				for(int i=0;i<eSum-1;i++)
					x=x*0.5;
					result+=x+"";
					return result;
				}
			}
			
			
			//结果为无穷或非数
			else if(allOne==true){
				boolean sZero=true;
				for(int i=0;i<eLength;i++){
					if(operand.charAt(i+1+eLength)=='1'){
						sZero=false;
						break;
					}
				}
				//结果为无穷
				if(sZero==true)
					return result+"Inf";
		      //非数
				else{
					return "NaN";
				}
			}

			return null;

	}
	
	/**
	 * 按位取反操作。<br/>
	 * 例：negation("00001001")
	 * @param operand 二进制表示的操作数
	 * @return operand按位取反的结果
	 */
	public String negation (String operand) {
			// TODO YOUR CODE HERE.
			String num="";
			for(int i=0;i<operand.length();i++){
				if(operand.charAt(i)=='1'){
					num+='0';
				}
				else{
					num+='1';
				}
			}
			return num;
	}
	
	/**
	 * 左移操作。<br/>
	 * 例：leftShift("00001001", 2)
	 * @param operand 二进制表示的操作数
	 * @param n 左移的位数
	 * @return operand左移n位的结果
	 */
	public String leftShift (String operand, int n) {
		// TODO YOUR CODE HERE.
		String num=null;
		num=operand.substring(n,operand.length());
		for(int i=0;i<n;i++){
			num+='0';
		}
		return num;
	}
	
	/**
	 * 逻辑右移操作。<br/>
	 * 例：logRightShift("11110110", 2)
	 * @param operand 二进制表示的操作数
	 * @param n 右移的位数
	 * @return operand逻辑右移n位的结果
	 */
	public String logRightShift (String operand, int n) {
		// TODO YOUR CODE HERE.
		String num=null;
		
			for(int i=0;i<n;i++){
				num+='0';
			}
			num+=operand.substring(0,operand.length()-n);
		
		num=num.substring(4, num.length());
		return num;
	}
	
	/**
	 * 算术右移操作。<br/>
	 * 例：logRightShift("11110110", 2)
	 * @param operand 二进制表示的操作数
	 * @param n 右移的位数
	 * @return operand算术右移n位的结果
	 */
	public String ariRightShift (String operand, int n) {
		// TODO YOUR CODE HERE.
		String num="";
		if(operand.charAt(0)=='1'){
			for(int i=0;i<n;i++){
				num+='1';
			}
			num+=operand.substring(0,operand.length()-n);
		}
		else{
			for(int i=0;i<n;i++){
				num+='0';
			}
			num+=operand.substring(0,operand.length()-n);
		}
		return num;
	}
	
	/**
	 * 全加器，对两位以及进位进行加法运算。<br/>
	 * 例：fullAdder('1', '1', '0')
	 * @param x 被加数的某一位，取0或1
	 * @param y 加数的某一位，取0或1
	 * @param c 低位对当前位的进位，取0或1
	 * @return 相加的结果，用长度为2的字符串表示，第1位表示进位，第2位表示和
	 */
	public String fullAdder (char x, char y, char c) {
		// TODO YOUR CODE HERE.
		String num="";
		char s0;
		char s;
		char c0;
		char c1;
		char xandy;
		//XOR
		if(x==y)
			s0='0';
		else
			s0='1';
		//XOR
		if(s0==c)
			s='0';
		else
			s='1';
		//AND
		if(c=='1'&&s0=='1')
			c0='1';
		else
			c0='0';
		//AND
		if(x=='1'&&y=='1')
			xandy='1';
		else
			xandy='0';
		//OR
		if(c0=='1'||xandy=='1')
			c1='1';
		else
			c1='0';
		
		num+=c1;
		num+=s;
		return num;
	}
	
	/**
	 * 4位先行进位加法器。要求采用{@link #fullAdder(char, char, char) fullAdder}来实现<br/>
	 * 例：claAdder("1001", "0001", '1')
	 * @param operand1 4位二进制表示的被加数
	 * @param operand2 4位二进制表示的加数
	 * @param c 低位对当前位的进位，取0或1
	 * @return 长度为5的字符串表示的计算结果，其中第1位是最高位进位，后4位是相加结果，其中进位不可以由循环获得
	 */
	public String claAdder (String operand1, String operand2, char c) {
		// TODO YOUR CODE HERE.
		String res="";
		char s='0';
		for(int i=3;i>=0;i--){
			s=fullAdder(operand1.charAt(i),operand2.charAt(i),c).charAt(1);
			c=fullAdder(operand1.charAt(i),operand2.charAt(i),c).charAt(0);
			res=s+res;
		}
		res=c+res;
		return res;
	}
	
	/**
	 * 加一器，实现操作数加1的运算。
	 * 需要采用与门、或门、异或门等模拟，
	 * 不可以直接调用{@link #fullAdder(char, char, char) fullAdder}、
	 * {@link #claAdder(String, String, char) claAdder}、
	 * {@link #adder(String, String, char, int) adder}、
	 * {@link #integerAddition(String, String, int) integerAddition}方法。<br/>
	 * 例：oneAdder("00001001")
	 * @param operand 二进制补码表示的操作数
	 * @return operand加1的结果，长度为operand的长度加1，其中第1位指示是否溢出（溢出为1，否则为0），其余位为相加结果
	 */
	public String oneAdder (String operand) {
		// TODO YOUR CODE HERE.
		String num=null;
		char c0='1';
		char s;
		for(int i=operand.length()-1;i>=0;i--){
			//XOR
			if((operand.charAt(i)=='0'&&c0=='0')||(operand.charAt(i)=='1'&&c0=='1'))
				s='0';
			else
				s='1';
			//AND
			if(operand.charAt(i)=='1'&&c0=='1')
				c0='1';
			else
				c0='0';
			
			num+=s;
		}
		num=num.substring(4, num.length());
		if(num.charAt(num.length()-1)==operand.charAt(0))
			num+='0';
		else
			num+='1';
		StringBuffer sb=new StringBuffer(num);
		sb=sb.reverse();
		num=sb.toString();
		return num;
	}
	/**
	 * 加法器，要求调用{@link #claAdder(String, String, char)}方法实现。<br/>
	 * 例：adder("0100", "0011", ‘0’, 8)
	 * @param operand1 二进制补码表示的被加数
	 * @param operand2 二进制补码表示的加数
	 * @param c 最低位进位
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相加结果
	 */
	public String adder (String operand1, String operand2, char c, int length) {
		// TODO YOUR CODE HERE.
		String res="";
		char o='0';
		//判断操作数的位数并进行补齐操作
		int l1=operand1.length();
		int l2=operand2.length();
		if(operand1.length()!=length){
			for(int i=0;i<(length-l1);i++){
				operand1=operand1.charAt(0)+operand1;
			}
		}else{}
		if(operand2.length()!=length){
			for(int i=0;i<(length-l2);i++){
				operand2=operand2.charAt(0)+operand2;	
			}
		}else{}
		
		int n=length/4;//需要cla的个数
		for(int i=n;i>0;i--){
			res=claAdder(operand1.substring((i-1)*4,4*i),operand2.substring((i-1)*4,4*i),c).substring(1)+res;
			c=claAdder(operand1.substring((i-1)*4,4*i),operand2.substring((i-1)*4,4*i),c).charAt(0);
		}
		//判断是否溢出
		if(operand1.charAt(0)==operand2.charAt(0)&&res.charAt(0)!=operand1.charAt(0))
			o='1';
		res=o+res;
				return res;
	}
	/**
	 * 整数加法，要求调用{@link #adder(String, String, char, int) adder}方法实现。<br/>
	 * 例：integerAddition("0100", "0011", 8)
	 * @param operand1 二进制补码表示的被加数
	 * @param operand2 二进制补码表示的加数
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相加结果
	 */
	public String integerAddition (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		String res="";
		res=adder(operand1,operand2,'0',length);
		return res;
	}
	
	/**
	 * 整数减法，可调用{@link #adder(String, String, char, int) adder}方法实现。<br/>
	 * 例：integerSubtraction("0100", "0011", 8)
	 * @param operand1 二进制补码表示的被减数
	 * @param operand2 二进制补码表示的减数
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相减结果
	 */
	public String integerSubtraction (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		operand2=negation(operand2);
		operand2=oneAdder(operand2).substring(1);
		String res="";
		res=adder(operand1,operand2,'0',length);
		return res;
	}
	
	/**
	 * 整数乘法，使用Booth算法实现，可调用{@link #adder(String, String, char, int) adder}等方法。<br/>
	 * 例：integerMultiplication("0100", "0011", 8)
	 * @param operand1 二进制补码表示的被乘数
	 * @param operand2 二进制补码表示的乘数
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的相乘结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相乘结果
	 */
	public String integerMultiplication (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		while(operand1.length()<length)
			operand1 = operand1.charAt(0)+operand1;
		while(operand2.length()<length)
			operand2 = operand2.charAt(0)+operand2;
		String cal = operand2 + "0";
		String product = "";
		for(int i=length;i>=1;i--)
			product = product + "0";
		String y = operand2;
		String result = product + y;
		for(int i=length;i>=1;i--){
			int temp = cal.charAt(i)-cal.charAt(i-1);
			product = result.substring(0,length);
			y = result.substring(length);
			if(temp==1){
				product =integerAddition(product,operand1,length).substring(1,length+1);
			}else if(temp==-1){
				product = integerSubtraction(product,operand1,length).substring(1,length+1);
			}
			result = product + y;
			result = ariRightShift(result,1);
		}
		String trueres=oneAdder(negation(result)).substring(1);
		if(result.charAt(0)=='1'&&Integer.parseInt(integerTrueValue(trueres))>Math.pow(2, length-1))
			result='1'+result.substring(length);
		else if(result.charAt(0)=='0'&&Integer.parseInt(integerTrueValue(result))>(Math.pow(2, length-1)-1))
			result='1'+result.substring(length);
		else
			result='0'+result.substring(length);
		return result;
	}
	
	/**
	 * 整数的不恢复余数除法，可调用{@link #adder(String, String, char, int) adder}等方法实现。<br/>
	 * 例：integerDivision("0100", "0011", 8)
	 * @param operand1 二进制补码表示的被除数
	 * @param operand2 二进制补码表示的除数
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为2*length+1的字符串表示的相除结果，其中第1位指示是否溢出（溢出为1，否则为0），其后length位为商，最后length位为余数
	 */
	public String integerDivision (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		while(operand1.length()<length)
			operand1 = operand1.charAt(0)+operand1;
		while(operand2.length()<length)
			operand2 = operand2.charAt(0)+operand2;
		boolean neednegation = false;
		if(operand1.charAt(0)!=operand2.charAt(0))
			neednegation = true;
		String remainder = "";
		for(int i=length;i>=1;i--)
			remainder = remainder + operand1.charAt(0);
		String quotient = operand1;
		String divisor = operand2;
		String all = remainder + quotient;
		for(int i=length;i>=1;i--){
		    boolean k=true;
			remainder = all.substring(0,length);
			quotient = all.substring(length);
			if(remainder.charAt(0)==divisor.charAt(0))
				remainder = integerSubtraction(remainder,divisor,length).substring(1);
			else
				remainder = integerAddition(remainder,divisor,length).substring(1);
			all = remainder + quotient;
			
			if(remainder.charAt(0)==divisor.charAt(0))
				k=true;
			else
				k=false;
			if(k)
			all = oneAdder(leftShift(all,1)).substring(1);
			else
		    all=leftShift(all,1);
		}
		
		boolean k=true;
		remainder = all.substring(0,length);
		quotient = all.substring(length);
		if(remainder.charAt(0)==divisor.charAt(0))
			remainder = integerSubtraction(remainder,divisor,length).substring(1);
		else
			remainder = integerAddition(remainder,divisor,length).substring(1);
		if(remainder.charAt(0)==divisor.charAt(0))
			k=true;
		else
			k=false;
	
		if(neednegation){
			if(quotient.charAt(0)=='1'){
				quotient=oneAdder(leftShift(quotient,1)).substring(1);
			if(k){
				quotient=oneAdder(quotient).substring(1);
			}}
			else
				quotient=oneAdder(negation(quotient)).substring(1);
		}
		else{
			if(quotient.charAt(0)=='0'){
				quotient=leftShift(quotient,1);
			if(k){
				quotient=oneAdder(quotient).substring(1);
			}}
			else
				quotient=oneAdder(negation(quotient)).substring(1);
		}
		if(remainder.charAt(0)!=operand1.charAt(0))
			remainder = integerSubtraction(remainder,divisor,length).substring(1);
		char p='0';
		if(Integer.parseInt(integerTrueValue(remainder))!=Integer.parseInt(integerTrueValue(operand1))%Integer.parseInt(integerTrueValue(operand2)))
			p='1';
		if(Integer.parseInt(integerTrueValue(quotient))!=Integer.parseInt(integerTrueValue(operand1))/Integer.parseInt(integerTrueValue(operand2)))
			p='1';
		return p+quotient + remainder;
	}
	
	/**
	 * 带符号整数加法，可以调用{@link #adder(String, String, char, int) adder}等方法，
	 * 但不能直接将操作数转换为补码后使用{@link #integerAddition(String, String, int) integerAddition}、
	 * {@link #integerSubtraction(String, String, int) integerSubtraction}来实现。<br/>
	 * 例：signedAddition("1100", "1011", 8)
	 * @param operand1 二进制原码表示的被加数，其中第1位为符号位
	 * @param operand2 二进制原码表示的加数，其中第1位为符号位
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度（不包含符号），当某个操作数的长度小于length时，需要将其长度扩展到length
	 * @return 长度为length+2的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），第2位为符号位，后length位是相加结果
	 */
	public String signedAddition (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		String sign="";
		sign+=operand1.charAt(0);
		sign+=operand2.charAt(0);
		operand1='0'+operand1.substring(1);
		operand2='0'+operand2.substring(1);
		String res="";
		switch(sign){
		case "00":res=adder(operand1,operand2,'0',length+4).substring(1);
		break;
		case "10":res=adder(negation(operand1),operand2,'1',length+4).substring(1);
		break;
		case "01":res=adder(operand1,negation(operand2),'1',length+4).substring(1);
		break;
		case "11":{
			res=oneAdder(negation(adder(operand1,operand2,'0',length+4).substring(1))).substring(1);
		break;
		}
		}
		char over;
		char sn=res.charAt(0);
		System.out.println(res);
		if(sn=='1'){
			res=oneAdder(negation(res)).substring(1);
			over=res.charAt(3);
			System.out.println(res);
			res='1'+res.substring(4);
			System.out.println(res);
		}
		else{
			over=res.charAt(3);
			System.out.println(res);
			res='0'+res.substring(4);
			
		}
		if(over=='1')
			res='1'+res;
		else
			res='0'+res;
		
		return res;
	}
	
	/**
	 * 浮点数加法，可调用{@link #signedAddition(String, String, int) signedAddition}等方法实现。<br/>
	 * 例：floatAddition("00111111010100000", "00111111001000000", 8, 8, 8)
	 * @param operand1 二进制表示的被加数
	 * @param operand2 二进制表示的加数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @param gLength 保护位的长度
	 * @return 长度为2+eLength+sLength的字符串表示的相加结果，其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatAddition (String operand1, String operand2, int eLength, int sLength, int gLength) {
		// TODO YOUR CODE HERE.
		return null;
	}
	
	/**
	 * 浮点数减法，可调用{@link #floatAddition(String, String, int, int, int) floatAddition}方法实现。<br/>
	 * 例：floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 8)
	 * @param operand1 二进制表示的被减数
	 * @param operand2 二进制表示的减数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @param gLength 保护位的长度
	 * @return 长度为2+eLength+sLength的字符串表示的相减结果，其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatSubtraction (String operand1, String operand2, int eLength, int sLength, int gLength) {
		// TODO YOUR CODE HERE.
		return null;
	}
	
	/**
	 * 浮点数乘法，可调用{@link #integerMultiplication(String, String, int) integerMultiplication}等方法实现。<br/>
	 * 例：floatMultiplication("00111110111000000", "00111111000000000", 8, 8)
	 * @param operand1 二进制表示的被乘数
	 * @param operand2 二进制表示的乘数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @return 长度为2+eLength+sLength的字符串表示的相乘结果,其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatMultiplication (String operand1, String operand2, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		return null;
	}
	

	/**
	 * 浮点数除法，可调用{@link #integerDivision(String, String, int) integerDivision}等方法实现。<br/>
	 * 例：floatDivision("00111110111000000", "00111111000000000", 8, 8)
	 * @param operand1 二进制表示的被除数
	 * @param operand2 二进制表示的除数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @return 长度为2+eLength+sLength的字符串表示的相乘结果,其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatDivision (String operand1, String operand2, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		return null;
	}
}
