package com.github.silvertreekr.mcutils.utils;

import com.github.silvertreekr.mcutils.MCUtils;
import com.github.silvertreekr.mcutils.manager.HeadDatabaseManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;



public class CustomItemBuilder {
    private static final MCUtils plugin = MCUtils.getInstance();
    private static final HeadDatabaseManager manager = plugin.getHDBManager();

    public CustomItemBuilder() {}

    // 후원자 기본
    public static ItemStack createPatronPresentBox() {
        ItemStack presentBox = manager.getHead("49163");
        if (presentBox == null || presentBox.isEmpty()) {
            plugin.getSLF4JLogger().warn("후원자 선물 상자 머리를 불러오지 못했습니다 ! 기본 아이템으로 대체합니다.");
            return new ItemStack(Material.PLAYER_HEAD);
        }

        ItemMeta itemMeta = presentBox.getItemMeta();
        List<Component> itemLore = new ArrayList<>();

        itemMeta.customName(MiniMessage.miniMessage().deserialize(
                "<#B8860B><bold>【 <gradient:#FFF9C4:#FFFFFF:#FFF9C4>후원</gradient> 】</bold></#B8860B><gradient:#FFF9C4:#FFFFFF:#FFF9C4>후원자 상자</gradient>"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<light_purple>후원자를 위해 운영진이 임의로 고른 장식 블럭 35종이 들어있다."
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>▶ 아이템 갯수 : 4개"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                ""
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<bold><aqua>▶ 우클릭하여 사용"
        ).decoration(TextDecoration.ITALIC, false));

        itemMeta.lore(itemLore);

        presentBox.setItemMeta(itemMeta);
        presentBox.setAmount(1);

        return presentBox;
    }

    // 후원자 음식 상자
    public static ItemStack createPatronFoodBox() {
        ItemStack presentBox = manager.getHead("49162");
        if (presentBox == null || presentBox.isEmpty()) {
            plugin.getSLF4JLogger().warn("후원자 음식 상자 머리를 불러오지 못했습니다 ! 기본 아이템으로 대체합니다.");
            return new ItemStack(Material.PLAYER_HEAD);
        }

        ItemMeta itemMeta = presentBox.getItemMeta();
        List<Component> itemLore = new ArrayList<>();

        itemMeta.customName(MiniMessage.miniMessage().deserialize(
                "【 장식 블럭 】 음식 세트"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>음식과 관련된 장식 블럭 6종이 들어있다."
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>▶ 아이템 갯수 : 6개"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                ""
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<bold><aqua>▶ 우클릭하여 사용"
        ).decoration(TextDecoration.ITALIC, false));

        itemMeta.lore(itemLore);

        presentBox.setItemMeta(itemMeta);
        presentBox.setAmount(1);

        return presentBox;
    }

    // 후원자 광석 상자
    public static ItemStack createPatronOreBox() {
        ItemStack presentBox = manager.getHead("49160");
        if (presentBox == null || presentBox.isEmpty()) {
            plugin.getSLF4JLogger().warn("후원자 광석 상자 머리를 불러오지 못했습니다 ! 기본 아이템으로 대체합니다.");
            return new ItemStack(Material.PLAYER_HEAD);
        }

        ItemMeta itemMeta = presentBox.getItemMeta();
        List<Component> itemLore = new ArrayList<>();

        itemMeta.customName(MiniMessage.miniMessage().deserialize(
                "【 장식 블럭 】 광석 세트"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>음식과 관련된 장식 블럭 12종이 들어있다."
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>▶ 아이템 갯수 : 12개"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                ""
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<bold><aqua>▶ 우클릭하여 사용"
        ).decoration(TextDecoration.ITALIC, false));

        itemMeta.lore(itemLore);

        presentBox.setItemMeta(itemMeta);
        presentBox.setAmount(1);

        return presentBox;
    }

    // 후원자 블럭 상자
    public static ItemStack createPatronBlockBox() {
        ItemStack presentBox = manager.getHead("49159");
        if (presentBox == null || presentBox.isEmpty()) {
            plugin.getSLF4JLogger().warn("후원자 블럭 상자 머리를 불러오지 못했습니다 ! 기본 아이템으로 대체합니다.");
            return new ItemStack(Material.PLAYER_HEAD);
        }

        ItemMeta itemMeta = presentBox.getItemMeta();
        List<Component> itemLore = new ArrayList<>();

        itemMeta.customName(MiniMessage.miniMessage().deserialize(
                "【 장식 블럭 】 블럭 세트"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>음식과 관련된 장식 블럭 10종이 들어있다."
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>▶ 아이템 갯수 : 10개"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                ""
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<bold><aqua>▶ 우클릭하여 사용"
        ).decoration(TextDecoration.ITALIC, false));

        itemMeta.lore(itemLore);

        presentBox.setItemMeta(itemMeta);
        presentBox.setAmount(1);

        return presentBox;
    }

    // 후원자 블럭 상자
    public static ItemStack createPatronEtcBox() {
        ItemStack presentBox = manager.getHead("49161");
        if (presentBox == null || presentBox.isEmpty()) {
            plugin.getSLF4JLogger().warn("후원자 기타 상자 머리를 불러오지 못했습니다 ! 기본 아이템으로 대체합니다.");
            return new ItemStack(Material.PLAYER_HEAD);
        }

        ItemMeta itemMeta = presentBox.getItemMeta();
        List<Component> itemLore = new ArrayList<>();

        itemMeta.customName(MiniMessage.miniMessage().deserialize(
                "【 장식 블럭 】 기타 세트"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>기타 장식 블럭 7종이 들어있다."
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>▶ 아이템 갯수 : 7개"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                ""
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<bold><aqua>▶ 우클릭하여 사용"
        ).decoration(TextDecoration.ITALIC, false));

        itemMeta.lore(itemLore);

        presentBox.setItemMeta(itemMeta);
        presentBox.setAmount(1);

        return presentBox;
    }

    // 후원자 음식 세트 6종
    public static List<ItemStack> createPatronFoodItem() {
        record HeadInfo(String id, String name) {}

        List<HeadInfo> headDataList = List.of(
                new HeadInfo("20764", "햄버거"),
                new HeadInfo("124197", "감자튀김"),
                new HeadInfo("34013", "샌드위치"),
                new HeadInfo("119984", "식빵"),
                new HeadInfo("25531", "커피"),
                new HeadInfo("126216", "사과")
        );

        List<ItemStack> resultList = new ArrayList<>();
        var mm = MiniMessage.miniMessage();

        for (HeadInfo data : headDataList) {
            ItemStack head = manager.getHead(data.id());

            if (head == null || head.isEmpty()) {
                continue;
            }

            ItemMeta meta = head.getItemMeta();
            if (meta != null) {
                meta.customName(mm.deserialize("【 장식 블럭 】 " + data.name()).decoration(TextDecoration.ITALIC, false));
                head.setItemMeta(meta);
            }

            head.setAmount(1);
            resultList.add(head);
        }

        return resultList;
    }

    // 후원자 광석 세트 12종
    public static List<ItemStack> createPatronOreItem() {
        record HeadInfo(String id, String name) {}

        List<HeadInfo> headDataList = List.of(
                new HeadInfo("48934", "심층암 다이아몬드 광석"),
                new HeadInfo("48935", "다이아몬드 광석"),
                new HeadInfo("48943", "금 광석"),
                new HeadInfo("48942", "심층암 금 광석"),
                new HeadInfo("48941", "철 광석"),
                new HeadInfo("48940", "심층암 철 광석"),
                new HeadInfo("48937", "석탄 광석"),
                new HeadInfo("48936", "심층암 석탄 광석"),
                new HeadInfo("48939", "청금석 광석"),
                new HeadInfo("48938", "심층암 청금석 광석"),
                new HeadInfo("48928", "심층암 레드스톤 광석"),
                new HeadInfo("48929", "레드스톤 광석")
        );

        List<ItemStack> resultList = new ArrayList<>();
        var mm = MiniMessage.miniMessage();

        for (HeadInfo data : headDataList) {
            ItemStack head = manager.getHead(data.id());

            if (head == null || head.isEmpty()) {
                continue;
            }

            ItemMeta meta = head.getItemMeta();
            if (meta != null) {
                meta.customName(mm.deserialize("【 장식 블럭 】 " + data.name()).decoration(TextDecoration.ITALIC, false));
                head.setItemMeta(meta);
            }

            head.setAmount(1);
            resultList.add(head);
        }

        return resultList;
    }

    // 후원자 블럭 세트 10종
    public static List<ItemStack> createPatronBlockItem() {
        record HeadInfo(String id, String name) {}

        List<HeadInfo> headDataList = List.of(
                new HeadInfo("51467", "다이아몬드가 담긴 상자"),
                new HeadInfo("74371", "엔더 상자"),
                new HeadInfo("46062", "참나무 원목이 담긴 상자"),
                new HeadInfo("34259", "잔디 블럭"),
                new HeadInfo("29442", "조약돌 블럭"),
                new HeadInfo("29438", "이끼 낀 조약돌 블럭"),
                new HeadInfo("113156", "돌 블럭"),
                new HeadInfo("77197", "흙 블럭"),
                new HeadInfo("63226", "참나무 원목"),
                new HeadInfo("65549", "제작대")
        );

        List<ItemStack> resultList = new ArrayList<>();
        var mm = MiniMessage.miniMessage();

        for (HeadInfo data : headDataList) {
            ItemStack head = manager.getHead(data.id());

            if (head == null || head.isEmpty()) {
                continue;
            }

            ItemMeta meta = head.getItemMeta();
            if (meta != null) {
                meta.customName(mm.deserialize("【 장식 블럭 】 " + data.name()).decoration(TextDecoration.ITALIC, false));
                head.setItemMeta(meta);
            }

            head.setAmount(1);
            resultList.add(head);
        }

        return resultList;
    }

    // 후원자 기타 세트 7종
    public static List<ItemStack> createPatronEtcItem() {
        record HeadInfo(String id, String name, String lore) {}

        List<HeadInfo> headDataList = List.of(
                new HeadInfo("125837", "커피 머신", "<white>커피 머신 치고는 너무 작지 않나?"),
                new HeadInfo("63953", "중형 동행 큐브", "<white>포탈 시리즈에 등장하는 중형 동행 큐브."),
                new HeadInfo("123499", "2x2 루빅 큐브", "<white>놀랍게도 2x2 루빅 큐브는 실존합니다."),
                new HeadInfo("54713", "트롤 페이스", "<white>우리 어디서 많이 뵙지 않았나요?"),
                new HeadInfo("81874", "지구", "<white>흐흐, 이제 지구는 내 손안에 있다."),
                new HeadInfo("96817", "카피바라", "<white>마인크래프트에 왜 추가되지 않은 것인가?"),
                new HeadInfo("127232", "병아리", "<white>지금 버전에서는 찾아볼 수 없습니다!")
        );

        List<ItemStack> resultList = new ArrayList<>();
        var mm = MiniMessage.miniMessage();

        for (HeadInfo data : headDataList) {
            ItemStack head = manager.getHead(data.id());

            if (head == null || head.isEmpty()) {
                continue;
            }

            ItemMeta meta = head.getItemMeta();
            if (meta != null) {
                meta.lore(List.of(mm.deserialize(data.lore).decoration(TextDecoration.ITALIC, false)));
                meta.customName(mm.deserialize("【 장식 블럭 】 " + data.name()).decoration(TextDecoration.ITALIC, false));
                head.setItemMeta(meta);
            }

            head.setAmount(1);
            resultList.add(head);
        }

        return resultList;
    }

    // 아야님 전용 선물 상자
    public static ItemStack createAyaPresentBox() {
        ItemStack presentBox = manager.getHead("49158");
        if (presentBox == null || presentBox.isEmpty()) {
            plugin.getSLF4JLogger().warn("아야님 전용 선물 상자 머리를 불러오지 못했습니다 ! 기본 아이템으로 대체합니다.");
            return new ItemStack(Material.PLAYER_HEAD);
        }

        ItemMeta itemMeta = presentBox.getItemMeta();
        List<Component> itemLore = new ArrayList<>();

        itemMeta.customName(MiniMessage.miniMessage().deserialize(
                "<gradient:#FF55FF:#9A5CC6:#FF55FF>아야님 전용 선물 상자</gradient>"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>집 꾸미기를 좋아하는 아야님을 위해 운영진이 고심하여 고른 장식 블럭 8종이 들어있다."
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>▶ 아이템 갯수 : 9개"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                ""
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<bold><aqua>▶ 우클릭하여 사용"
        ).decoration(TextDecoration.ITALIC, false));

        itemMeta.lore(itemLore);

        presentBox.setItemMeta(itemMeta);
        presentBox.setAmount(1);

        return presentBox;
    }

    // 아야님 장식 블럭 8종
    public static List<ItemStack> createAyaItem() {
        record HeadInfo(String id, String name, int amount) {}

        List<HeadInfo> headDataList = List.of(
                new HeadInfo("112410", "구운 치킨", 1),
                new HeadInfo("121397", "케이크", 1),
                new HeadInfo("66920", "구운 고기", 1),
                new HeadInfo("42567", "눈사람 피규어", 1),
                new HeadInfo("4122", "딸기", 1),
                new HeadInfo("874", "갈색 가방", 2),
                new HeadInfo("127298", "수박", 1),
                new HeadInfo("126763", "펭귄 옷을 입은 피규어", 1)
        );

        List<ItemStack> resultList = new ArrayList<>();
        var mm = MiniMessage.miniMessage();

        for (HeadInfo data : headDataList) {
            ItemStack head = manager.getHead(data.id());

            if (head == null || head.isEmpty()) {
                continue;
            }

            ItemMeta meta = head.getItemMeta();
            if (meta != null) {
                meta.customName(mm.deserialize("【 장식 블럭 】 " + data.name()).decoration(TextDecoration.ITALIC, false));
                head.setItemMeta(meta);
            }

            head.setAmount(data.amount());
            resultList.add(head);
        }

        return resultList;
    }

    // 종식님 전용 선물 상자
    public static ItemStack createJongSickPresentBox() {
        ItemStack presentBox = manager.getHead("49158");
        if (presentBox == null || presentBox.isEmpty()) {
            plugin.getSLF4JLogger().warn("종식님 전용 선물 상자 머리를 불러오지 못했습니다 ! 기본 아이템으로 대체합니다.");
            return new ItemStack(Material.PLAYER_HEAD);
        }

        ItemMeta itemMeta = presentBox.getItemMeta();
        List<Component> itemLore = new ArrayList<>();

        itemMeta.customName(MiniMessage.miniMessage().deserialize(
                "<gradient:#A8FF78:#F7FF00:#A8FF78>종식님 전용 선물 상자</gradient>"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>지하 세계에서 거주하는 종식님을 위해 운영진이 고심하여 고른 장식 블럭 8종이 들어있다."
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>▶ 아이템 갯수 : 8개"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                ""
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<bold><aqua>▶ 우클릭하여 사용"
        ).decoration(TextDecoration.ITALIC, false));

        itemMeta.lore(itemLore);

        presentBox.setItemMeta(itemMeta);
        presentBox.setAmount(1);

        return presentBox;
    }

    // 종식님 장식 블럭 8종
    public static List<ItemStack> createJongSickItem() {
        record HeadInfo(String id, String name) {}

        List<HeadInfo> headDataList = List.of(
                new HeadInfo("116472", "괴물A"),
                new HeadInfo("3013", "괴물B"),
                new HeadInfo("1439", "썩은 히로빈"),
                new HeadInfo("111706", "망가진 히로빈"),
                new HeadInfo("26678", "참수된 스티브"),
                new HeadInfo("97511", "죽은 트롤페이스"),
                new HeadInfo("90680", "감염된 스티브"),
                new HeadInfo("98283", "괴물C")
        );

        List<ItemStack> resultList = new ArrayList<>();
        var mm = MiniMessage.miniMessage();

        for (HeadInfo data : headDataList) {
            ItemStack head = manager.getHead(data.id());

            if (head == null || head.isEmpty()) {
                continue;
            }

            ItemMeta meta = head.getItemMeta();
            if (meta != null) {
                meta.customName(mm.deserialize("【 장식 블럭 】 " + data.name()).decoration(TextDecoration.ITALIC, false));
                head.setItemMeta(meta);
            }

            head.setAmount(1);
            resultList.add(head);
        }

        return resultList;
    }

    // 무지님 전용 선물 상자
    public static ItemStack createMuzziPresentBox() {
        ItemStack presentBox = manager.getHead("49158");
        if (presentBox == null || presentBox.isEmpty()) {
            plugin.getSLF4JLogger().warn("무지님 전용 선물 상자 머리를 불러오지 못했습니다 ! 기본 아이템으로 대체합니다.");
            return new ItemStack(Material.PLAYER_HEAD);
        }

        ItemMeta itemMeta = presentBox.getItemMeta();
        List<Component> itemLore = new ArrayList<>();

        itemMeta.customName(MiniMessage.miniMessage().deserialize(
                "<gradient:#FFF59D:#FFFDE7:#FFF59D>무지님 전용 선물 상자</gradient>"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>과일을 좋아하는 무지님을 위해 운영진이 고심하여 고른 장식 블럭 8종이 들어있다."
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<white>▶ 아이템 갯수 : 8개"
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                ""
        ).decoration(TextDecoration.ITALIC, false));
        itemLore.add(MiniMessage.miniMessage().deserialize(
                "<bold><aqua>▶ 우클릭하여 사용"
        ).decoration(TextDecoration.ITALIC, false));

        itemMeta.lore(itemLore);

        presentBox.setItemMeta(itemMeta);
        presentBox.setAmount(1);

        return presentBox;
    }

    // 무지님 장식 블럭 8종
    public static List<ItemStack> createMuzziItem() {
        record HeadInfo(String id, String name) {}

        List<HeadInfo> headDataList = List.of(
                new HeadInfo("127298", "수박"),
                new HeadInfo("4122", "딸기"),
                new HeadInfo("95650", "복숭아"),
                new HeadInfo("52706", "파인애플"),
                new HeadInfo("71401", "오렌지"),
                new HeadInfo("11", "포도"),
                new HeadInfo("127296", "용과"),
                new HeadInfo("9127294", "체리")
        );

        List<ItemStack> resultList = new ArrayList<>();
        var mm = MiniMessage.miniMessage();

        for (HeadInfo data : headDataList) {
            ItemStack head = manager.getHead(data.id());

            if (head == null || head.isEmpty()) {
                continue;
            }

            ItemMeta meta = head.getItemMeta();
            if (meta != null) {
                meta.customName(mm.deserialize("【 장식 블럭 】 " + data.name()).decoration(TextDecoration.ITALIC, false));
                head.setItemMeta(meta);
            }

            head.setAmount(1);
            resultList.add(head);
        }

        return resultList;
    }

    public static ItemStack createGim() {
        ItemStack itemStack = new ItemStack(Material.DRIED_KELP);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.customName(MiniMessage.miniMessage().deserialize(
                "<#B8860B><bold>【<gradient:#FFF9C4:#FFFFFF:#FFF9C4>보상</gradient>】</bold></#B8860B> <reset><light_purple>김"
        ).decoration(TextDecoration.ITALIC, false));
        itemMeta.lore(List.of(MiniMessage.miniMessage().deserialize(
                "<yellow>이 세상에서 제일 맛있는 음식입니다."
        ).decoration(TextDecoration.ITALIC, false)));
        itemStack.setItemMeta(itemMeta);
        itemStack.setAmount(64);

        return itemStack;
    }

    public static ItemStack createStopDevelopReward() {
        ItemStack elytra = new ItemStack(Material.ELYTRA);
        ItemMeta itemMeta = elytra.getItemMeta();
        itemMeta.customName(MiniMessage.miniMessage().deserialize(
                "<#B8860B><bold>【<gradient:#FFF9C4:#FFFFFF:#FFF9C4>보상</gradient>】</bold></#B8860B> <yellow>겉날개"
        ).decoration(TextDecoration.ITALIC, false));
        itemMeta.lore(List.of(MiniMessage.miniMessage().deserialize(
                "<yellow>여러분들이 그토록 염원하던 겉날개입니다."
        ).decoration(TextDecoration.ITALIC, false)));
        elytra.setItemMeta(itemMeta);
        elytra.setAmount(1);

        return elytra;
    }
}