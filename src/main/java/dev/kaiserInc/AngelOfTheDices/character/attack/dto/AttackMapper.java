package dev.kaiserInc.AngelOfTheDices.character.attack.dto;

import dev.kaiserInc.AngelOfTheDices.character.attack.Attack;

public final class AttackMapper {

    private AttackMapper() {}

    public static Attack toEntity(AttackRequestDTO dto) {
        Attack attack = new Attack();
        attack.setName(dto.name());
        attack.setType(dto.type());
        attack.setTestAttribute(dto.testAttribute());
        attack.setTestExpertise(dto.testExpertise());
        attack.setTestBonus(dto.testBonus() != null ? dto.testBonus() : 0);
        attack.setDamageDiceQuantity(dto.damageDiceQuantity());
        attack.setDamageDiceType(dto.damageDiceType());
        attack.setDamageBonus(dto.damageBonus() != null ? dto.damageBonus() : 0);
        attack.setCriticalThreshold(dto.criticalThreshold());
        attack.setCriticalMultiplier(dto.criticalMultiplier());
        attack.setRange(dto.range());
        attack.setSpecial(dto.special());
        return attack;
    }

    public static void updateEntityFromDTO(AttackRequestDTO dto, Attack attack) {
        attack.setName(dto.name());
        attack.setType(dto.type());
        attack.setTestAttribute(dto.testAttribute());
        attack.setTestExpertise(dto.testExpertise());
        attack.setTestBonus(dto.testBonus() != null ? dto.testBonus() : 0);
        attack.setDamageDiceQuantity(dto.damageDiceQuantity());
        attack.setDamageDiceType(dto.damageDiceType());
        attack.setDamageBonus(dto.damageBonus() != null ? dto.damageBonus() : 0);
        attack.setCriticalThreshold(dto.criticalThreshold());
        attack.setCriticalMultiplier(dto.criticalMultiplier());
        attack.setRange(dto.range());
        attack.setSpecial(dto.special());
    }

    public static AttackResponseDTO toResponseDTO(Attack attack) {
        return new AttackResponseDTO(
                attack.getId(),
                attack.getName(),
                attack.getType(),
                attack.getTestAttribute(),
                attack.getTestExpertise(),
                attack.getTestBonus(),
                attack.getDamageDiceQuantity(),
                attack.getDamageDiceType(),
                attack.getDamageBonus(),
                attack.getCriticalThreshold(),
                attack.getCriticalMultiplier(),
                attack.getRange(),
                attack.getSpecial()
        );
    }
}
