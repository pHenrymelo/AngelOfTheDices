package dev.kaiserInc.AngelOfTheDices.character.origin;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.kaiserInc.AngelOfTheDices.character.Sourcebook;
import dev.kaiserInc.AngelOfTheDices.character.expertise.ExpertiseName;
import lombok.Getter;

import java.util.List;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Origin {

    // --- Origens do Livro de Regras Básico (CORE_RULEBOOK) ---
    ACADEMIC("Acadêmico", "Saber é Poder", List.of(ExpertiseName.SCIENCE, ExpertiseName.INVESTIGATION), Sourcebook.CORE_RULEBOOK),
    HEALTH_AGENT("Agente de Saúde", "Técnica Medicinal", List.of(ExpertiseName.INTUITION, ExpertiseName.MEDICINE), Sourcebook.CORE_RULEBOOK),
    AMNESIAC("Amnésico", "Vislumbres do Passado", List.of(), Sourcebook.CORE_RULEBOOK),
    ARTIST("Artista", "Magnum Opus", List.of(ExpertiseName.ART, ExpertiseName.DECEIT), Sourcebook.CORE_RULEBOOK),
    ATHLETE("Atleta", "110%", List.of(ExpertiseName.ACROBATICS, ExpertiseName.ATHLETICS), Sourcebook.CORE_RULEBOOK),
    CHEF("Chef", "Ingrediente Secreto", List.of(ExpertiseName.STAMINA, ExpertiseName.PROFESSION), Sourcebook.CORE_RULEBOOK),
    CRIMINAL("Criminoso", "O Crime Compensa", List.of(ExpertiseName.CRIME, ExpertiseName.STEALTH), Sourcebook.CORE_RULEBOOK),
    REPENTANT_CULTIST("Cultista Arrependido", "Traços do Outro Lado", List.of(ExpertiseName.OCCULTISM, ExpertiseName.FAITH), Sourcebook.CORE_RULEBOOK),
    STRAY("Desgarrado", "Calejado", List.of(ExpertiseName.STAMINA, ExpertiseName.SURVIVALISM), Sourcebook.CORE_RULEBOOK),
    ENGINEER("Engenheiro", "Ferramenta Favorita", List.of(ExpertiseName.PROFESSION, ExpertiseName.TECHNOLOGY), Sourcebook.CORE_RULEBOOK),
    EXECUTIVE("Executivo", "Processo Otimizado", List.of(ExpertiseName.DIPLOMACY, ExpertiseName.PROFESSION), Sourcebook.CORE_RULEBOOK),
    INVESTIGATOR("Investigador", "Faro para Pistas", List.of(ExpertiseName.INVESTIGATION, ExpertiseName.PERCEPTION), Sourcebook.CORE_RULEBOOK),
    FIGHTER("Lutador", "Mão Pesada", List.of(ExpertiseName.FIGHT, ExpertiseName.REACTION), Sourcebook.CORE_RULEBOOK),
    MOGUL("Magnata", "Patrocinador da Ordem", List.of(ExpertiseName.DIPLOMACY, ExpertiseName.NAVIGATE), Sourcebook.CORE_RULEBOOK),
    MERCENARY("Mercenário", "Posição de Combate", List.of(ExpertiseName.INITIATIVE, ExpertiseName.INTIMIDATION), Sourcebook.CORE_RULEBOOK),
    SOLDIER("Militar", "Para Bellum", List.of(ExpertiseName.PRECISION, ExpertiseName.TACTIC), Sourcebook.CORE_RULEBOOK),
    WORKER("Operário", "Ferramenta de Trabalho", List.of(ExpertiseName.STAMINA, ExpertiseName.PROFESSION), Sourcebook.CORE_RULEBOOK),
    POLICE_OFFICER("Policial", "Patrulha", List.of(ExpertiseName.PERCEPTION, ExpertiseName.PRECISION), Sourcebook.CORE_RULEBOOK),
    RELIGIOUS("Religioso", "Acalentar", List.of(ExpertiseName.FAITH, ExpertiseName.WILL), Sourcebook.CORE_RULEBOOK),
    PUBLIC_SERVANT("Servidor Público", "Espírito Cívico", List.of(ExpertiseName.INTUITION, ExpertiseName.WILL), Sourcebook.CORE_RULEBOOK),
    CONSPIRACY_THEORIST("Teórico da Conspiração", "Eu Já Sabia", List.of(ExpertiseName.INVESTIGATION, ExpertiseName.OCCULTISM), Sourcebook.CORE_RULEBOOK),
    IT_TECHNICIAN("T.I.", "Motor de Busca", List.of(ExpertiseName.INVESTIGATION, ExpertiseName.TECHNOLOGY), Sourcebook.CORE_RULEBOOK),
    RURAL_WORKER("Trabalhador Rural", "Desbravador", List.of(ExpertiseName.DRESSAGE, ExpertiseName.SURVIVALISM), Sourcebook.CORE_RULEBOOK),
    SWINDLER("Trambiqueiro", "Impostor", List.of(ExpertiseName.CRIME, ExpertiseName.DECEIT), Sourcebook.CORE_RULEBOOK),
    UNIVERSITY_STUDENT("Universitário", "Dedicação", List.of(ExpertiseName.ACTUALITY, ExpertiseName.INVESTIGATION), Sourcebook.CORE_RULEBOOK),
    VICTIM("Vítima", "Cicatrizes Psicológicas", List.of(ExpertiseName.REACTION, ExpertiseName.WILL), Sourcebook.CORE_RULEBOOK),

    // --- Origens do Suplemento "Sobrevivendo ao Horror" (SUPPLEMENT) ---
    ANIMAL_FRIEND("Amigo dos Animais", "Companheiro Animal", List.of(ExpertiseName.DRESSAGE, ExpertiseName.PERCEPTION), Sourcebook.SUPPLEMENT),
    ASTRONAUT("Astronauta", "Acostumado ao Extremo", List.of(ExpertiseName.SCIENCE, ExpertiseName.STAMINA), Sourcebook.SUPPLEMENT),
    OTHER_SIDE_CHEF("Chef do Outro Lado", "Fome do Outro Lado", List.of(ExpertiseName.OCCULTISM, ExpertiseName.PROFESSION), Sourcebook.SUPPLEMENT),
    COLLEGIAL("Colegial", "Poder da Amizade", List.of(ExpertiseName.ACTUALITY, ExpertiseName.TECHNOLOGY), Sourcebook.SUPPLEMENT),
    COSPLAYER("Cosplayer", "Não é fantasia, é cosplay!", List.of(ExpertiseName.ART, ExpertiseName.WILL), Sourcebook.SUPPLEMENT),
    DIPLOMAT("Diplomata", "Conexões", List.of(ExpertiseName.ACTUALITY, ExpertiseName.DIPLOMACY), Sourcebook.SUPPLEMENT),
    EXPLORER("Explorador", "Manual do Sobrevivente", List.of(ExpertiseName.STAMINA, ExpertiseName.SURVIVALISM), Sourcebook.SUPPLEMENT),
    EXPERIMENT("Experimento", "Mutação", List.of(ExpertiseName.ATHLETICS, ExpertiseName.STAMINA), Sourcebook.SUPPLEMENT),
    CREATURE_FANATIC("Fanático por Criaturas", "Conhecimento Oculto", List.of(ExpertiseName.INVESTIGATION, ExpertiseName.OCCULTISM), Sourcebook.SUPPLEMENT),
    PHOTOGRAPHER("Fotógrafo", "Através da Lente", List.of(ExpertiseName.ART, ExpertiseName.PERCEPTION), Sourcebook.SUPPLEMENT),
    PARANORMAL_INVENTOR("Inventor Paranormal", "Invenção Paranormal", List.of(ExpertiseName.PROFESSION, ExpertiseName.WILL), Sourcebook.SUPPLEMENT),
    YOUNG_MYSTIC("Jovem Místico", "A Culpa é das Estrelas", List.of(ExpertiseName.OCCULTISM, ExpertiseName.FAITH), Sourcebook.SUPPLEMENT),
    NIGHT_SHIFT_CORONER("Legista do Turno da Noite", "Luto Habitual", List.of(ExpertiseName.SCIENCE, ExpertiseName.MEDICINE), Sourcebook.SUPPLEMENT),
    WOODSMAN("Mateiro", "Mapa Celeste", List.of(ExpertiseName.PERCEPTION, ExpertiseName.SURVIVALISM), Sourcebook.SUPPLEMENT),
    DIVER("Mergulhador", "Fôlego de Nadador", List.of(ExpertiseName.ATHLETICS, ExpertiseName.STAMINA), Sourcebook.SUPPLEMENT),
    DRIVER("Motorista", "Mãos no Volante", List.of(ExpertiseName.NAVIGATE, ExpertiseName.REACTION), Sourcebook.SUPPLEMENT),
    NERD_ENTHUSIAST("Nerd Entusiasta", "O Inteligentão", List.of(ExpertiseName.SCIENCE, ExpertiseName.TECHNOLOGY), Sourcebook.SUPPLEMENT),
    PROPHESIED("Profetizado", "Luta ou Fuga", List.of(ExpertiseName.WILL), Sourcebook.SUPPLEMENT),
    PSYCHOLOGIST("Psicólogo", "Terapia", List.of(ExpertiseName.INTUITION, ExpertiseName.PROFESSION), Sourcebook.SUPPLEMENT),
    INVESTIGATIVE_REPORTER("Repórter Investigativo", "Encontrar a Verdade", List.of(ExpertiseName.ACTUALITY, ExpertiseName.INVESTIGATION), Sourcebook.SUPPLEMENT);

    private final String displayName;
    private final String powerDescription;
    private final List<ExpertiseName> skillOptions;
    private final Sourcebook source;

    Origin(String displayName, String powerDescription, List<ExpertiseName> skillOptions, Sourcebook source) {
        this.displayName = displayName;
        this.powerDescription = powerDescription;
        this.skillOptions = skillOptions;
        this.source = source;
    }

    public String getName() {
        return this.name();
    }
}
