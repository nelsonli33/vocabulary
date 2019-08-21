package com.lee.vocabulary.web;

public interface ControllerConstants {

    interface Pages {
        String HomePage = "pages/index";

        interface Vocabulary {
            String VocabularyWriteEngPage = "pages/vocabulary/vocabularyWriteEnglishPage";
            String VocabularyChooseChiPage = "pages/vocabulary/vocabularyChooseChinesePage";
            String VocabularyWrongAnswerPage = "pages/vocabulary/vocabularyWrongAnswerPage";
            String UserVocabularyStatisticPage = "pages/vocabulary/userVocabularyStatisticPage";
        }

        interface User {
            String UserRegisterPage = "pages/user/registerPage";
            String UserLoginPage = "pages/user/loginPage";
        }
    }
}
