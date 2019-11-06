class StargateUi
  def buildWithoutMap
    `cd .. && ./gradlew components:ui:buildTest`
  end

  def buildWithMap
    `cd .. && ./gradlew components:ui:build`
  end
end
