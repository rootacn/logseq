const path = require('path')

module.exports = {
  packagerConfig: {
    icon: './icons/logseq.icns'
  },

  makers: [
    {
      'name': '@electron-forge/maker-squirrel',
      'config': {
        'name': 'Logseq'
      }
    },
    {
      name: '@electron-forge/maker-dmg',
      config: {
        background: './img/dmg-bg.png',
        format: 'ULFO',
        icon: './icons/logseq.icns',
        name: 'Logseq'
      }
    },
    {
      name: '@electron-forge/maker-zip',
      platforms: ['darwin', 'linux']
    }
  ],

  publishers: [
    {
      name: '@electron-forge/publisher-github',
      config: {
        repository: {
          owner: 'logseq',
          name: 'logseq'
        },
        prerelease: true
      }
    }
  ]
}
