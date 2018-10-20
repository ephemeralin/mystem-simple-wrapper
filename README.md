# mystem-simple-wrapper
[![Build Status](https://travis-ci.org/ephemeralin/mystem-simple-wrapper.svg?branch=master)](https://travis-ci.org/ephemeralin/mystem-simple-wrapper)
[![codecov](https://codecov.io/gh/ephemeralin/mystem-simple-wrapper/branch/master/graph/badge.svg)](https://codecov.io/gh/ephemeralin/mystem-simple-wrapper)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Simple Java wrapper for [Yandex MyStem](https://tech.yandex.ru/mystem/) (morphological analysis for Russian text). It enables integration with the excellent tool from within a Java application and allows to work with the tool in a object-oriented way. 

### Usage example
```Java
Options options = new Options()
        .setOutputFormat(Options.OutputFormatType.JSON)
            .setPrintGramInfo(false)
            .setPrintWeight(true)
            .setPrintAllSuggestions(true);
        Stemmer stemmer = new Stemmer(options);
        List<Word> results = stemmer.stem(
                "Глокая куздра штеко будланула бокра и курдячит бокрёнка.");
```

### License
Mystem Simple Wrapper is licensed under [MIT License](https://opensource.org/licenses/MIT).