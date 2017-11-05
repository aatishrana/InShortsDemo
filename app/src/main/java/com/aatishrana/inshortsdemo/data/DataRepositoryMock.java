package com.aatishrana.inshortsdemo.data;

import com.aatishrana.inshortsdemo.model.CardItem;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by Aatish on 11/5/2017.
 */

public class DataRepositoryMock implements DataRepository
{

    private final String sampleJson;

    public DataRepositoryMock()
    {
        sampleJson = "{\n" +
                "    \"min_card_id\": \"id_ccHnkxcG\",\n" +
                "    \"card_list\": [\n" +
                "        {\n" +
                "            \"id\": \"id_welcome\",\n" +
                "            \"rank\": 1,\n" +
                "            \"version\": 0,\n" +
                "            \"card_obj\": {\n" +
                "                \"categories\": [\n" +
                "                    \"welcome\"\n" +
                "                ],\n" +
                "                \"created_at\": 1509524623,\n" +
                "                \"hw_ratio\": 1.1,\n" +
                "                \"id\": \"id_welcome\",\n" +
                "                \"share_image\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/10may/inshorts_image_1494430124319_191.jpg?output-format=webp\",\n" +
                "                \"share_text\": \"Download 'INPIX' to experience the world through pictures. https://getinpix.com\",\n" +
                "                \"title\": \"WELCOME_CARD\",\n" +
                "                \"type\": \"IMAGE\",\n" +
                "                \"data\": {\n" +
                "                    \"height\": 1189,\n" +
                "                    \"image_url\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/10may/inshorts_image_1494430145241_498.jpg?output-format=webp\",\n" +
                "                    \"placeholder\": {\n" +
                "                        \"alt\": \"\",\n" +
                "                        \"data\": \"#FFFFFF\",\n" +
                "                        \"type\": \"RGB\"\n" +
                "                    },\n" +
                "                    \"width\": 1080\n" +
                "                },\n" +
                "                \"version\": 0,\n" +
                "                \"tenant\": \"ENGLISH\"\n" +
                "            }\n" +
                "        },\n" +
                "\t\t{\n" +
                "            \"id\": \"id_j1UlLwdW\",\n" +
                "            \"rank\": 47,\n" +
                "            \"version\": 0,\n" +
                "            \"card_obj\": {\n" +
                "                \"categories\": [\n" +
                "                    \"news\",\n" +
                "                    \"top_news\"\n" +
                "                ],\n" +
                "                \"created_at\": 1509770528,\n" +
                "                \"hw_ratio\": 1.25,\n" +
                "                \"id\": \"id_j1UlLwdW\",\n" +
                "                \"share_image\": \"\",\n" +
                "                \"share_text\": \"Download 'INPIX' to experience the world through pictures. https://getinpix.com\",\n" +
                "                \"title\": \"Do you know which are the longest flights in the world?\",\n" +
                "                \"type\": \"MULTI_IMAGE_CARD\",\n" +
                "                \"data\": {\n" +
                "                    \"card_data_list\": {\n" +
                "                        \"position_0\": {\n" +
                "                            \"full_story_url\": \"https://www.forbes.com/sites/ericrosen/2017/11/01/a-current-ranking-of-the-longest-airline-flights-in-the-world/#6318c4c64607\",\n" +
                "                            \"height\": 1350,\n" +
                "                            \"id\": \"position_0\",\n" +
                "                            \"image_url\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/3nov/inshorts_image_1509717855756_678.jpg?output-format=webp\",\n" +
                "                            \"placeholder\": {\n" +
                "                                \"alt\": \"\",\n" +
                "                                \"data\": \"#FFFFFF\",\n" +
                "                                \"type\": \"RGB\"\n" +
                "                            },\n" +
                "                            \"position\": \"0\",\n" +
                "                            \"width\": 1080\n" +
                "                        },\n" +
                "                        \"position_1\": {\n" +
                "                            \"full_story_url\": \"https://www.forbes.com/sites/ericrosen/2017/11/01/a-current-ranking-of-the-longest-airline-flights-in-the-world/#6318c4c64607\",\n" +
                "                            \"height\": 1350,\n" +
                "                            \"id\": \"position_1\",\n" +
                "                            \"image_url\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/3nov/inshorts_image_1509717851341_100.jpg?output-format=webp\",\n" +
                "                            \"placeholder\": {\n" +
                "                                \"alt\": \"\",\n" +
                "                                \"data\": \"#FFFFFF\",\n" +
                "                                \"type\": \"RGB\"\n" +
                "                            },\n" +
                "                            \"position\": 1,\n" +
                "                            \"width\": 1080\n" +
                "                        },\n" +
                "                        \"position_10\":{\n" +
                "                            \"full_story_url\": \"https://www.forbes.com/sites/ericrosen/2017/11/01/a-current-ranking-of-the-longest-airline-flights-in-the-world/#6318c4c64607\",\n" +
                "                            \"height\": 1350,\n" +
                "                            \"id\": \"position_10\",\n" +
                "                            \"image_url\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/3nov/inshorts_image_1509717855466_989.jpg?output-format=webp\",\n" +
                "                            \"placeholder\": {\n" +
                "                                \"alt\": \"\",\n" +
                "                                \"data\": \"#FFFFFF\",\n" +
                "                                \"type\": \"RGB\"\n" +
                "                            },\n" +
                "                            \"position\": 10,\n" +
                "                            \"width\": 1080\n" +
                "                        },\n" +
                "                        \"position_2\": {\n" +
                "                            \"full_story_url\": \"https://www.forbes.com/sites/ericrosen/2017/11/01/a-current-ranking-of-the-longest-airline-flights-in-the-world/#6318c4c64607\",\n" +
                "                            \"height\": 1350,\n" +
                "                            \"id\": \"position_2\",\n" +
                "                            \"image_url\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/3nov/inshorts_image_1509717851198_180.jpg?output-format=webp\",\n" +
                "                            \"placeholder\": {\n" +
                "                                \"alt\": \"\",\n" +
                "                                \"data\": \"#FFFFFF\",\n" +
                "                                \"type\": \"RGB\"\n" +
                "                            },\n" +
                "                            \"position\": 2,\n" +
                "                            \"width\": 1080\n" +
                "                        },\n" +
                "                        \"position_3\": {\n" +
                "                            \"full_story_url\": \"https://www.forbes.com/sites/ericrosen/2017/11/01/a-current-ranking-of-the-longest-airline-flights-in-the-world/#6318c4c64607\",\n" +
                "                            \"height\": 1350,\n" +
                "                            \"id\": \"position_3\",\n" +
                "                            \"image_url\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/3nov/inshorts_image_1509717852029_689.jpg?output-format=webp\",\n" +
                "                            \"placeholder\": {\n" +
                "                                \"alt\": \"\",\n" +
                "                                \"data\": \"#FFFFFF\",\n" +
                "                                \"type\": \"RGB\"\n" +
                "                            },\n" +
                "                            \"position\": 3,\n" +
                "                            \"width\": 1080\n" +
                "                        },\n" +
                "                        \"position_4\": {\n" +
                "                            \"full_story_url\": \"https://www.forbes.com/sites/ericrosen/2017/11/01/a-current-ranking-of-the-longest-airline-flights-in-the-world/#6318c4c64607\",\n" +
                "                            \"height\": 1350,\n" +
                "                            \"id\": \"position_4\",\n" +
                "                            \"image_url\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/3nov/inshorts_image_1509717851044_771.jpg?output-format=webp\",\n" +
                "                            \"placeholder\": {\n" +
                "                                \"alt\": \"\",\n" +
                "                                \"data\": \"#FFFFFF\",\n" +
                "                                \"type\": \"RGB\"\n" +
                "                            },\n" +
                "                            \"position\": 4,\n" +
                "                            \"width\": 1080\n" +
                "                        },\n" +
                "                        \"position_5\": {\n" +
                "                            \"full_story_url\": \"https://www.forbes.com/sites/ericrosen/2017/11/01/a-current-ranking-of-the-longest-airline-flights-in-the-world/#6318c4c64607\",\n" +
                "                            \"height\": 1350,\n" +
                "                            \"id\": \"position_5\",\n" +
                "                            \"image_url\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/3nov/inshorts_image_1509717851287_469.jpg?output-format=webp\",\n" +
                "                            \"placeholder\": {\n" +
                "                                \"alt\": \"\",\n" +
                "                                \"data\": \"#FFFFFF\",\n" +
                "                                \"type\": \"RGB\"\n" +
                "                            },\n" +
                "                            \"position\": 5,\n" +
                "                            \"width\": 1080\n" +
                "                        },\n" +
                "                        \"position_6\": {\n" +
                "                            \"full_story_url\": \"https://www.forbes.com/sites/ericrosen/2017/11/01/a-current-ranking-of-the-longest-airline-flights-in-the-world/#6318c4c64607\",\n" +
                "                            \"height\": 1350,\n" +
                "                            \"id\": \"position_6\",\n" +
                "                            \"image_url\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/3nov/inshorts_image_1509717851386_690.jpg?output-format=webp\",\n" +
                "                            \"placeholder\": {\n" +
                "                                \"alt\": \"\",\n" +
                "                                \"data\": \"#FFFFFF\",\n" +
                "                                \"type\": \"RGB\"\n" +
                "                            },\n" +
                "                            \"position\": 6,\n" +
                "                            \"width\": 1080\n" +
                "                        },\n" +
                "                        \"position_7\": {\n" +
                "                            \"full_story_url\": \"https://www.forbes.com/sites/ericrosen/2017/11/01/a-current-ranking-of-the-longest-airline-flights-in-the-world/#6318c4c64607\",\n" +
                "                            \"height\": 1350,\n" +
                "                            \"id\": \"position_7\",\n" +
                "                            \"image_url\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/3nov/inshorts_image_1509717854693_452.jpg?output-format=webp\",\n" +
                "                            \"placeholder\": {\n" +
                "                                \"alt\": \"\",\n" +
                "                                \"data\": \"#FFFFFF\",\n" +
                "                                \"type\": \"RGB\"\n" +
                "                            },\n" +
                "                            \"position\": 7,\n" +
                "                            \"width\": 1080\n" +
                "                        },\n" +
                "                        \"position_8\": {\n" +
                "                            \"full_story_url\": \"https://www.forbes.com/sites/ericrosen/2017/11/01/a-current-ranking-of-the-longest-airline-flights-in-the-world/#6318c4c64607\",\n" +
                "                            \"height\": 1350,\n" +
                "                            \"id\": \"position_8\",\n" +
                "                            \"image_url\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/3nov/inshorts_image_1509717854582_589.jpg?output-format=webp\",\n" +
                "                            \"placeholder\": {\n" +
                "                                \"alt\": \"\",\n" +
                "                                \"data\": \"#FFFFFF\",\n" +
                "                                \"type\": \"RGB\"\n" +
                "                            },\n" +
                "                            \"position\": 8,\n" +
                "                            \"width\": 1080\n" +
                "                        },\n" +
                "                        \"position_9\": {\n" +
                "                            \"full_story_url\": \"https://www.forbes.com/sites/ericrosen/2017/11/01/a-current-ranking-of-the-longest-airline-flights-in-the-world/#6318c4c64607\",\n" +
                "                            \"height\": 1350,\n" +
                "                            \"id\": \"position_9\",\n" +
                "                            \"image_url\": \"http://images.newsinshorts.com.edgesuite.net/app_assets/images/2017/3nov/inshorts_image_1509717855289_955.jpg?output-format=webp\",\n" +
                "                            \"placeholder\": {\n" +
                "                                \"alt\": \"\",\n" +
                "                                \"data\": \"#FFFFFF\",\n" +
                "                                \"type\": \"RGB\"\n" +
                "                            },\n" +
                "                            \"position\": 9,\n" +
                "                            \"width\": 1080\n" +
                "                        }\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": 0,\n" +
                "                \"tenant\": \"ENGLISH\"\n" +
                "            }\n" +
                "        }\n" +
                "\t\t]\n" +
                "}";
    }

    @Override
    public Observable<List<CardItem>> getData()
    {
        return Observable.create(new ObservableOnSubscribe<List<CardItem>>()
        {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<CardItem>> e) throws Exception
            {
                e.onNext(MyJsonParser.parseJson(new JSONObject(sampleJson)));
                e.onComplete();
            }
        });
    }
}
