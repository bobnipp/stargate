package com.afresearchlab.stargate.spechelpers

import org.springframework.stereotype.Component

@Component
class ResourceLoader {
    def getContract(String filename) {
        getFile(filename + '.contract')
    }

    def getFixture(String filename) {
        getFile(filename + '.fixture')
    }

    private getFile(String filename) {
        loadFile('contracts/' + filename + '.json')
    }

    private loadFile(String path) {
        def loader = getClass().getClassLoader()
        new File(loader.getResource(path).getFile())
    }
}
