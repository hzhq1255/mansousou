package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.es.SuggestEs;
import cn.zucc.edu.mansousou.entity.es.SuggestText;
import cn.zucc.edu.mansousou.repository.es.SuggestEsRepository;
import cn.zucc.edu.mansousou.service.inter.SuggestService;
import cn.zucc.edu.mansousou.util.Result;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/24 23:09
 * @desc:
 */
@Service
@Transactional
public class SuggestServiceImpl implements SuggestService {

    @Autowired
    SuggestEsRepository suggestEsRepository;

    @Autowired
    ElasticsearchRestTemplate esTemplate;


    @Override
    public List<SuggestText> getSuggest(String keyword) {
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        CompletionSuggestionBuilder fullPinyinSuggest =
                new CompletionSuggestionBuilder("fullPinyin")
                .text(keyword).size(10).skipDuplicates(true);
        CompletionSuggestionBuilder prefixPinyinSuggest =
                new CompletionSuggestionBuilder("prefixPinyin")
                .text(keyword).size(10).skipDuplicates(true);
        CompletionSuggestionBuilder suggestTextSuggest =
                new CompletionSuggestionBuilder("suggestText")
                .text(keyword).size(10).skipDuplicates(true);
        suggestBuilder = suggestBuilder
                .addSuggestion("fullPinyinSuggest",fullPinyinSuggest)
                .addSuggestion("prefixPinyinSuggest",prefixPinyinSuggest)
                .addSuggestion("chineseWordSuggest",suggestTextSuggest);
        SearchResponse searchResponse =
                esTemplate.suggest(suggestBuilder,"suggest");
        Suggest.Suggestion fullPinyinSuggestion = searchResponse.getSuggest().getSuggestion("fullPinyinSuggest");
        Suggest.Suggestion prefixPinyinSuggestion = searchResponse.getSuggest().getSuggestion("prefixPinyinSuggest");
        Suggest.Suggestion suggestTextSuggestion = searchResponse.getSuggest().getSuggestion("chineseWordSuggest");
        List<String> result = new ArrayList<>();
        List<SuggestText> suggestTexts = new ArrayList<>();
        List<Suggest.Suggestion.Entry> entries = suggestTextSuggestion.getEntries();

        for (Suggest.Suggestion.Entry entry : entries){
            List<Suggest.Suggestion.Entry.Option> options = entry.getOptions();
            for (Suggest.Suggestion.Entry.Option option : options){
                result.add(option.getText().toString());
            }
        }

        if (result.size() < 10){
            List<Suggest.Suggestion.Entry> fullPinyinEntries = fullPinyinSuggestion.getEntries();
            for (Suggest.Suggestion.Entry entry : fullPinyinEntries){
                List<Suggest.Suggestion.Entry.Option> options = entry.getOptions();
                for (Suggest.Suggestion.Entry.Option option: options){
                    if (result.size() < 10){
                        String text = option.getText().toString();
                        if (!result.contains(text)){
                            result.add(option.getText().toString());
                        }
                    }
                }
            }
        }
        if (result.size() == 0){
            List<Suggest.Suggestion.Entry> prefixPinyinEntries = prefixPinyinSuggestion.getEntries();
            for (Suggest.Suggestion.Entry entry : prefixPinyinEntries){
                List<Suggest.Suggestion.Entry.Option> options = entry.getOptions();
                for (Suggest.Suggestion.Entry.Option option: options){
                    String text = option.getText().toString();
                    if (!result.contains(text)){
                        result.add(option.getText().toString());
                    }
                }
            }
        }
        int index = 0;
        for (String string:result){
            index++;
            SuggestText suggestText = new SuggestText();
            suggestText.setIndex(index);
            suggestText.setValue(string);
            suggestTexts.add(suggestText);
        }

        return suggestTexts;
    }
}
