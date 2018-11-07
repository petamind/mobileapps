//
//  ViewController.swift
//  Xylophone
//
//  Created by Angela Yu on 27/01/2016.
//  Copyright © 2016 London App Brewery. All rights reserved.\
//  Modified by Tung Nguyen 2018
//

import UIKit
import AudioToolbox
import AVFoundation


class ViewController: UIViewController{
    
    var audioPlayer : AVAudioPlayer!
    override func viewDidLoad() {
        super.viewDidLoad()
    }



    @IBAction func notePressed(_ sender: UIButton) {
        print(sender.tag)
        //playSound(i: Int8(sender.tag))
        playSound2(i: Int8(sender.tag))
    
    }
    
    func playSound(i: Int8) {
        if let url = Bundle.main.url(forResource: "note\(i)", withExtension: "wav"){
            var mySound: SystemSoundID = 0
            AudioServicesCreateSystemSoundID(url as CFURL, &mySound)
            AudioServicesPlaySystemSound(mySound)
        }
    }

    //78udemy
    func playSound2(i: Int8) {
        
        if let url = Bundle.main.url(forResource: "note\(i)", withExtension: "wav"){
            do {
                audioPlayer = try AVAudioPlayer(contentsOf: url)
            } catch {
                print(error)
            }
            
            audioPlayer.play()
        }
    }
}
