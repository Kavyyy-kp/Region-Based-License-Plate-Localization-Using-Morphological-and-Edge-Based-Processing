# License Plate Localization Using Classical Image Processing Techniques

## Introduction

This project implements a lightweight Automatic License Plate Localization (ALPL) system in Java using classical image processing techniques. The system combines Sobel edge detection, Otsu thresholding, morphological operations, and connected component analysis to identify license plate regions from vehicle images.

Unlike modern approaches that rely on machine learning or deep learning models, this implementation focuses on deterministic image processing methods. As a result, the system remains computationally efficient, interpretable, and suitable for deployment in resource-constrained environments.

The primary objective of the project is to accurately localize license plate regions before further processing such as Optical Character Recognition (OCR).

---

## Features

- Fully implemented in Java
- No machine learning or deep learning models required
- Sobel-based edge detection
- Otsu automatic thresholding
- Morphological region enhancement
- Connected component analysis
- Geometric filtering using area and aspect ratio constraints
- Lightweight and computationally efficient

---

## Project Structure

```text
ANPR/
│
├── src/
│   └── ANPR GUI
│   └── Binarization
│   └── DetectNoPlate
│   └── Edge_detection
│   └── Grayscale
│   └── ImageViewer
│   └── Morphology
│   └── PlateLocalization
│
├── bin/
│   └── Compiled Java classes
│
├── images/
│   └── Vehicle test images
│
├── .settings/
├── .classpath
└── .project
```

---

## Processing Pipeline

```text
Input Vehicle Image
          │
          ▼
Grayscale Conversion
          │
          ▼
Sobel Edge Detection
          │
          ▼
Otsu Thresholding
          │
          ▼
Morphological Closing
          │
          ▼
Connected Component Analysis
          │
          ▼
Geometric Filtering
          │
          ▼
License Plate Localization
```

---

## Methodology

### 1. Grayscale Conversion

The input RGB image is converted into an 8-bit grayscale image using a luminance-weighted formula:

```
I(x,y) = 0.2126R + 0.7152G + 0.0722B
```

This transformation reduces image complexity while preserving structural information required for edge analysis.

**Purpose**

- Reduce computational cost
- Remove unnecessary colour information
- Prepare the image for edge detection

---

### 2. Edge Detection

After grayscale conversion, the Sobel operator is applied to detect regions with significant intensity variation. Since license plates typically contain strong rectangular boundaries and dense character patterns, edge information plays a crucial role in localization.

Horizontal gradient kernel:

```text
-1  0  1
-2  0  2
-1  0  1
```

Vertical gradient kernel:

```text
 1  2  1
 0  0  0
-1 -2 -1
```

The resulting gradient magnitude image highlights prominent object boundaries and candidate plate regions.

**Purpose**

- Detect structural boundaries
- Highlight license plate edges
- Separate foreground objects from the background

---

### 3. Binarization

The gradient image is converted into a binary image using Otsu's thresholding method.

Otsu's algorithm automatically determines an optimal threshold value by maximizing the variance between foreground and background classes. This adaptive approach allows the system to perform consistently under varying lighting conditions.

**Purpose**

- Separate potential plate regions from the background
- Generate a binary representation for morphology processing
- Improve segmentation quality

---

### 4. Morphological Closing

Morphological closing is applied using a structuring element to connect fragmented edge regions and improve object continuity.

```
I • B = (I ⊕ B) ⊖ B
```

where:

- ⊕ = Dilation
- ⊖ = Erosion

Dilation bridges small gaps between disconnected edge segments, while erosion removes unwanted expansions introduced during dilation.

**Purpose**

- Connect broken boundaries
- Fill small gaps within candidate regions
- Improve region coherence

---

### 5. Connected Component Analysis

An 8-connected component labeling algorithm is used to identify distinct foreground regions within the binary image.

Each connected region is assigned a unique label and enclosed within a bounding box for further analysis.

**Purpose**

- Identify candidate plate regions
- Extract region boundaries
- Prepare candidates for geometric filtering

---

### 6. Geometric Validation

Not all connected regions correspond to license plates. Therefore, each candidate region is evaluated using geometric constraints.

#### Area Filtering

Regions that are excessively small or large are discarded.

#### Aspect Ratio Filtering

License plates generally exhibit a characteristic width-to-height ratio. Regions outside the predefined range are eliminated.

#### Final Selection

The candidate that best satisfies the geometric requirements is selected as the license plate region and projected onto the original image using a bounding box.

---

## Dataset

The system was evaluated using a dataset consisting of 97 vehicle images captured under varying environmental conditions.

Dataset variations include:

- Different illumination levels
- Multiple viewing angles
- Complex backgrounds
- Partial shadows
- Different vehicle distances

These conditions provide a realistic assessment of localization performance.

---

## Results

| Metric | Value |
|----------|----------|
| Total Test Images | 97 |
| Localization Accuracy | 72% |
| Average Processing Time | ~967 ms |

The results demonstrate that classical image processing techniques can achieve acceptable license plate localization performance without requiring machine learning models or large-scale training datasets.

---

## Failure Cases

Localization failures were primarily observed under the following conditions:

- Severe illumination variation
- Low-contrast license plates
- Motion blur
- Highly cluttered backgrounds
- Partial plate occlusions

These scenarios reduce edge quality and negatively impact candidate extraction.

---

## Requirements

### Software

- Java Development Kit (JDK) 8 or later
- Eclipse IDE (recommended)

### Dependencies

No external libraries are required.

---

## How to Run

### Step 1

Import the project into Eclipse:

```text
File → Open Projects from File System
```

### Step 2

Place vehicle images inside:

```text
images/
```

### Step 3

Build the project.

### Step 4

Run the main Java class.

### Step 5

The system processes each image and displays the localized license plate with a bounding box overlay.

---

## Limitations

- Fixed geometric thresholds may not generalize to all plate formats.
- Performance decreases under severe illumination changes.
- Detection accuracy depends heavily on edge quality.
- Perspective distortion is not explicitly handled.
- Character recognition is not included.

---

## Future Improvements

Potential enhancements include:

- Adaptive contrast enhancement
- Dynamic geometric thresholding
- Multi-scale morphology processing
- Perspective correction
- OCR integration
- Hybrid machine learning-assisted verification

---

## References

1. Hermary, R., Tochon, G., Puybareau, E., Kirszenberg, A., & Angulo, J. (2022). *Learning grayscale mathematical morphology with smooth morphological layers*. Journal of Mathematical Imaging and Vision, 64(7), 736–753.

2. Jadhav, A. V., Dongre, O. K., Shinde, T. K., Patil, D. S., & Dewan, J. H. (2022). *A study on approaches for automatic number plate recognition systems*. Advances in Data and Information Sciences, 647–658.

3. Vijayalakshmi, N., Sindhu, S., & Suriya, S. (2020). *Automatic vehicle number recognition system using character segmentation and morphological algorithm*. IEEE ICADEE.

4. Zhai, Z., Benssali, S., & Ramalingam, S. (2010). *License plate localisation based on morphological operations*. International Journal of Advanced Computer Science and Applications, 1(3), 7–12.
