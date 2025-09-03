package org.example.delivery.service;

public final class PasswordPolicy {

    /**
     * 패스워드 검증
     * <pre>
     * 대문자/소문자/숫자/특수문자 중 3가지 이상 + 길이 ≥12
     * </pre>
     *
     */
    public static boolean valid(String raw) {
        if (raw == null || raw.length() < 12) return false;
        int types = 0;
        if (raw.chars().anyMatch(Character::isUpperCase)) types++;
        if (raw.chars().anyMatch(Character::isLowerCase)) types++;
        if (raw.chars().anyMatch(Character::isDigit))     types++;
        if (raw.chars().anyMatch(c -> "!@#$%^&*()-_=+[]{};:'\",.<>/?\\|`~".indexOf(c) >= 0)) types++;
        return types >= 3;
    }
}
