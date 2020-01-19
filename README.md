# GHCbot for AWS Lambda
[GitHub Contribution Bot](https://github.com/T45K/GithubContributionBot)のKotlinでの再実装版かつAWS Lambdaで使用可能にしたバージョンです<br>

## 使い方
1. [ここ](https://github.com/T45K/GithubContributionBot)を参考にして`/src/main/resources/info.properties`に必要な情報を入力してください．
2. `./gradlew build`を実行してください
3. `/build/libs/GHCBonK.jar`をAWS Lambdaにアップロードしてください．実行メソッドは`t45k.ghcbonk.MyLambda::handleRequest`を指定してください．
4. CloudWatch Eventsでcronを設定してください．英時間を基準にしているので，日本時間の0:00に実行したければ毎日15:00に実行するように指定してください
