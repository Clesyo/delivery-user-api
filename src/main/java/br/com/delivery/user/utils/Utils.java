package br.com.delivery.user.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.delivery.user.DeliveryUserContext;
import br.com.delivery.user.enums.RoleType;
import br.com.delivery.user.model.Role;

@Component
public abstract class Utils {

	private static DeliveryUserContext context;

	public static List<Role> convertUserTypeRoles(RoleType... types) {
		return Arrays.stream(types).map(type -> new Role(type.name())).collect(Collectors.toList());
	}

//	public static String generateRandomString(Integer length) {
//		return RandomStringUtils.random(length, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
//	}

	public DeliveryUserContext getContext() {
		return context;
	}

	public static void setContext(DeliveryUserContext context) {
		Utils.context = context;
	}

	public static String validPhone(String phone) {
		return phone.replaceAll("[\\s()-]", "");
	}

	public static String validCpf(String cpf) {
		return cpf.replaceAll("[\\s()-.]", "");
	}

	public static boolean isEmpty(String param) {
		return StringUtils.isEmpty(param);
	}

	public static boolean isPhoneNumberValid(String phoneNumber) {

		String processedPhone = phoneNumber.replaceAll("[\\s()-]", "");

		// verifica se tem a qtde correta de numeros
		if (processedPhone.length() != 11)
			return false;

		// Se tiver 11 caracteres, verificar se começa com 9 o celular
		if (Integer.parseInt(processedPhone.substring(2, 3)) != 9)
			return false;

		String last8Numbers = processedPhone.substring(3, 11);

		if (last8Numbers.equals("99999999") || last8Numbers.equals("88888888") || last8Numbers.equals("77777777")
				|| last8Numbers.equals("66666666") || last8Numbers.equals("55555555") || last8Numbers.equals("44444444")
				|| last8Numbers.equals("33333333") || last8Numbers.equals("22222222")
				|| last8Numbers.equals("11111111"))
			return false;

		// DDDs validos
		List<String> dddCodes = Arrays.asList("11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "24",
				"27", "28", "31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45", "46", "47", "48",
				"49", "51", "53", "54", "55", "61", "62", "64", "63", "65", "66", "67", "68", "69", "71", "73", "74",
				"75", "77", "79", "81", "82", "83", "84", "85", "86", "87", "88", "89", "91", "92", "93", "94", "95",
				"96", "97", "98", "99");

		// verifica se o DDD é valido
		if (!dddCodes.contains(processedPhone.substring(0, 2)))
			return false;

		// se passar por todas as validações acima, o formato está correto.
		return true;
	}

	public static boolean isCpfValido(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static boolean isCnpjValido(String cnpj) {
		// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222")
				|| cnpj.equals("33333333333333") || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
				|| cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || cnpj.equals("88888888888888")
				|| cnpj.equals("99999999999999") || (cnpj.length() != 14))
			return (false);

		char dig13, dig14;
		int sm, i, r, num, peso;

		// "try" - protege o código para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 2;
			for (i = 11; i >= 0; i--) {
				// converte o i-ésimo caractere do CNPJ em um número:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posição de '0' na tabela ASCII)
				num = (int) (cnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig13 = '0';
			else
				dig13 = (char) ((11 - r) + 48);

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 2;
			for (i = 12; i >= 0; i--) {
				num = (int) (cnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig14 = '0';
			else
				dig14 = (char) ((11 - r) + 48);

			// Verifica se os dígitos calculados conferem com os dígitos informados.
			if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static String normalizeToQuery(String query) {
		String newQuery = StringUtils.stripAccents(query);
		newQuery = StringUtils.upperCase(newQuery);
		newQuery = StringUtils.deleteWhitespace(newQuery);

		StringBuilder ret = new StringBuilder();
		Matcher matches = Pattern.compile("[A-Z0-9]").matcher(newQuery);
		while (matches.find()) {
			ret.append(matches.group(0));
		}

		return ret.toString();
	}

	public static boolean isEmailValid(String email) {
		if (StringUtils.isEmpty(email))
			return false;

		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
	}

	public static String dateFormatedToString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(formatter);
	}

	public static LocalDate dateFormatedToLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, formatter);
	}

}
