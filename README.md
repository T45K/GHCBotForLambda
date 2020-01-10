# GHCbot for AWS Lambda
[GitHub Contribution Bot](https://github.com/T45K/GithubContributionBot)のKotlinでの再実装版かつAWS Lambdaで使用可能にしたバージョンです<br>

## 使い方
1. [ここ](https://github.com/T45K/GithubContributionBot)を参考にして`/src/main/kotlin/t45k/ghcbonk/resource/resource.property`に必要な情報を入力してください．
2. `./gradlew build`
3. `/build/libs/GHCBonK.jar`をAWS Lambdaにアップロードしてください．実行メソッドは`t45k.ghcbonk.MyLambda::handleRequest`を指定してください．
4. CloudWatch Eventsでcronを設定してください．
